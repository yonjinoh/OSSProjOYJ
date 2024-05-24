package com.example.mytestapp.chat

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.adapters.MessagesAdapter
import com.example.mytestapp.model.request.ChatMessage
import com.example.mytestapp.service.ChatService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatActivity : AppCompatActivity() {
    private lateinit var messages: MutableList<ChatMessage>
    private lateinit var adapter: MessagesAdapter
    private lateinit var messageInput: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var chatService: ChatService
    private lateinit var currentUserId: String
    private lateinit var targetUserId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        initializeComponents()
        setupButtonListeners()
    }

    private fun initializeComponents() {
        currentUserId = "currentUserId"
        targetUserId = "targetUserId"

        messageInput = findViewById(R.id.chat_input)
        val sendButton = findViewById<ImageView>(R.id.message_send)
        recyclerView = findViewById(R.id.chat_recyclerView)

        messages = mutableListOf()
        adapter = MessagesAdapter(messages)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://yourserver.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        chatService = retrofit.create(ChatService::class.java)
        loadMessages()
    }

    private fun setupButtonListeners() {
        val sendButton = findViewById<ImageView>(R.id.message_send)
        sendButton.setOnClickListener {
            val text = messageInput.text.toString()
            if (text.isNotEmpty()) {
                sendMessage(text)
                messageInput.text.clear()
            } else {
                Toast.makeText(this, "메시지를 입력하세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadMessages() {
        chatService.getMessages().enqueue(object : Callback<List<ChatMessage>> {
            override fun onResponse(call: Call<List<ChatMessage>>, response: Response<List<ChatMessage>>) {
                if (response.isSuccessful) {
                    messages.clear()
                    messages.addAll(response.body()!!)
                    adapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(messages.size - 1)
                }
            }

            override fun onFailure(call: Call<List<ChatMessage>>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "메시지를 불러오지 못했습니다: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun sendMessage(messageText: String) {
        val message = ChatMessage("", currentUserId, targetUserId, currentUserId, messageText, "")
        chatService.postMessage(message).enqueue(object : Callback<ChatMessage> {
            override fun onResponse(call: Call<ChatMessage>, response: Response<ChatMessage>) {
                if (response.isSuccessful) {
                    loadMessages()
                }
            }

            override fun onFailure(call: Call<ChatMessage>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "메시지를 보내지 못했습니다: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
