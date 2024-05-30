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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.adapters.ChatAdapter
import com.example.mytestapp.model.request.ChatMessage
import com.example.mytestapp.viewmodel.ChatRoomViewModel
import com.example.mytestapp.matchmaking.MatchmakingActivity
import com.example.mytestapp.websocket.WebSocketManager
import org.json.JSONObject

class ChatActivity : AppCompatActivity() {

    private lateinit var adapter: ChatAdapter
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

    private val chatRoomViewModel: ChatRoomViewModel by viewModels()

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

        adapter = ChatAdapter(currentUserId, listOf())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        chatRoomViewModel.chatMessages.observe(this, Observer { messages ->
            adapter.submitList(messages)
            recyclerView.scrollToPosition(messages.size - 1)
        })

        chatRoomViewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage.isNotEmpty()) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
            }
        })

        webSocketManager = WebSocketManager(
            onMessageReceived = { message -> runOnUiThread {
                chatRoomViewModel.addMessage(message)
            }},
            onConnectionFailed = { error -> runOnUiThread {
                Toast.makeText(this, "WebSocket 연결 실패: $error", Toast.LENGTH_LONG).show()
            }}
        )

        webSocketManager.connect()
        chatRoomViewModel.loadMessages(currentUserId, targetUserId)
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
                    R.id.matching_user -> {
                        val intent = Intent(this, MatchmakingActivity::class.java)
                        intent.putExtra("targetUserId", targetUserId)
                        intent.putExtra("currentUserId", currentUserId)
                        startActivity(intent)
                        true
                    }
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
        val messageData = ChatMessage(
            messageId = "", // 서버에서 생성
            senderId = currentUserId,
            receiverId = targetUserId,
            content = messageText,
            timestamp = "", // 서버에서 생성
            formattedTimestamp = "", // 서버에서 생성
            senderName = currentUserName
        )

        val jsonObject = JSONObject().apply {
            put("messageId", messageData.messageId)
            put("senderId", messageData.senderId)
            put("receiverId", messageData.receiverId)
            put("content", messageData.content)
            put("timestamp", messageData.timestamp)
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
