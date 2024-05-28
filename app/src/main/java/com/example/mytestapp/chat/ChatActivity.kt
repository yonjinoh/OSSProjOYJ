package com.example.mytestapp.chat

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.adapters.MessagesAdapter
import com.example.mytestapp.model.request.ChatMessage
import com.example.mytestapp.websocket.WebSocketManager
import org.json.JSONObject

class ChatActivity : AppCompatActivity() {

    private lateinit var messages: MutableList<ChatMessage>
    private lateinit var adapter: MessagesAdapter
    private lateinit var messageInput: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var sendButton: Button
    private lateinit var imageMenu: ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var btnBack: Button
    private lateinit var currentUserId: String
    private lateinit var currentUserName: String
    private lateinit var targetUserId: String
    private lateinit var targetUserName: String
    private lateinit var webSocketManager: WebSocketManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        targetUserId = intent.getStringExtra("targetUserId") ?: "Unknown User"
        targetUserName = intent.getStringExtra("targetUserName") ?: "Unknown User"

        val sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)
        currentUserId = sharedPreferences.getString("userId", "defaultUserId") ?: "defaultUserId"
        currentUserName = sharedPreferences.getString("userName", "defaultUserName") ?: "defaultUserName"

        initializeComponents()
        setupButtonListeners()

        webSocketManager = WebSocketManager(
            url = "ws://yourserver.com/socket",
            onMessageReceived = { message -> runOnUiThread {
                messages.add(message)
                adapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
            }},
            onConnectionFailed = { error -> runOnUiThread {
                Toast.makeText(this, "WebSocket 연결 실패: $error", Toast.LENGTH_LONG).show()
            }}
        )
    }

    private fun initializeComponents() {
        messageInput = findViewById(R.id.chat_input)
        sendButton = findViewById(R.id.message_send)
        recyclerView = findViewById(R.id.chat_recyclerView)
        imageMenu = findViewById(R.id.imageMenu)
        drawerLayout = findViewById(R.id.drawerLayout)
        btnBack = findViewById(R.id.btn_back)

        val textTitle = findViewById<TextView>(R.id.textTitle)
        textTitle.text = targetUserName

        messages = mutableListOf()
        adapter = MessagesAdapter(messages)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setupButtonListeners() {
        sendButton.setOnClickListener {
            val text = messageInput.text.toString()
            if (text.isNotEmpty()) {
                sendMessage(text)
                messageInput.text.clear()
            } else {
                Toast.makeText(this, "메시지를 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }

        imageMenu.setOnClickListener {
            val popupMenu = PopupMenu(this, imageMenu)
            popupMenu.inflate(R.menu.chat_menu)
            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when (item.itemId) {
                    R.id.action_report -> {
                        val intent = Intent(this, ReportUserActivity::class.java)
                        intent.putExtra("targetUserId", targetUserId)
                        startActivity(intent)
                        true
                    }
                    R.id.action_block -> {
                        val intent = Intent(this, BlockUserActivity::class.java)
                        intent.putExtra("targetUserId", targetUserId)
                        startActivity(intent)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }
    }

    private fun sendMessage(messageText: String) {
        val messageId = ""
        val timestamp = System.currentTimeMillis().toString()

        val messageData = ChatMessage(
            messageId = messageId,
            userId = currentUserId,
            receiverId = targetUserId,
            senderId = currentUserId,
            content = messageText,
            formattedTimestamp = timestamp,
            senderName = currentUserName
        )

        val jsonObject = JSONObject().apply {
            put("messageId", messageData.messageId)
            put("userId", messageData.userId)
            put("receiverId", messageData.receiverId)
            put("senderId", messageData.senderId)
            put("content", messageData.content)
            put("formattedTimestamp", messageData.formattedTimestamp)
            put("senderName", messageData.senderName)
        }
        webSocketManager.sendMessage(jsonObject.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketManager.disconnect()
    }
}
