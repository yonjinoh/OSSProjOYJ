package com.example.mytestapp.websocket

import android.util.Log
import com.example.mytestapp.model.response.ChatMessage
import okhttp3.*
import okio.ByteString
import org.json.JSONObject

class WebSocketManager(
    private val chatRoomId: Int?,
    private val onMessageReceived: (ChatMessage?) -> Unit,
    private val onConnectionFailed: (String) -> Unit
) {
    private lateinit var webSocket: WebSocket
    private val client = OkHttpClient()

    private val serverUrl = "ws://fbda-58-140-213-197.ngrok-free.app/ws/chat/$chatRoomId/"

    fun connect() {
        val request = Request.Builder().url(serverUrl).build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                Log.d("WebSocket", "Connected to server")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                val jsonObject = JSONObject(text)
                val chatMessage = if (jsonObject.has("CHistoryID")) {
                    ChatMessage(
                        CHistoryID = jsonObject.getString("CHistoryID"),
                        senderID = jsonObject.getString("senderID"),
                        receiverID = jsonObject.getString("receiverID"),
                        content = jsonObject.getString("content")
                    )
                } else {
                    null
                }
                onMessageReceived(chatMessage)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                val text = bytes.utf8()
                val jsonObject = JSONObject(text)
                val chatMessage = if (jsonObject.has("CHistoryID")) {
                    ChatMessage(
                        CHistoryID = jsonObject.getString("CHistoryID"),
                        senderID = jsonObject.getString("senderID"),
                        receiverID = jsonObject.getString("receiverID"),
                        content = jsonObject.getString("content")
                    )
                } else {
                    null
                }
                onMessageReceived(chatMessage)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                onConnectionFailed(t.message ?: "Unknown error")
            }
        })
    }

    fun sendMessage(message: String) {
        webSocket.send(message)
    }

    fun disconnect() {
        webSocket.close(1000, "Closing connection")
    }
}
