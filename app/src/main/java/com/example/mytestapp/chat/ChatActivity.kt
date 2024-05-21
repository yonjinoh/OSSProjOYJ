package com.example.mytestapp.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.adapters.MessagesAdapter
import com.example.mytestapp.model.request.ChatMessage
import com.example.mytestapp.R
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
    private lateinit var chatService: matchingService
    private lateinit var currentUserId: String  // 현재 로그인한 사용자의 ID를 저장
    private lateinit var targetUserId: String  // 채팅 상대방의 ID를 저장

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        initializeComponents()
        setupButtonListeners()
    }

    private fun initializeComponents() {
        // 설정 필요: 로그인 세션에서 ID 추출
        currentUserId = "currentUserId"
        targetUserId = "targetUserId"  // 채팅 상대방의 ID 설정

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

        val menuMore = findViewById<ImageView>(R.id.imageMenu)
        menuMore.setOnClickListener { view ->
            onMoreOptionsClicked(view)
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
                    loadMessages()  // 메시지 목록 새로고침
                }
            }

            override fun onFailure(call: Call<ChatMessage>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "메시지를 보내지 못했습니다: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun onMoreOptionsClicked(view: View?) {
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.chat_menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.matching_user -> {
                    handleMatchingUser()
                    true
                }
                R.id.action_block -> {
                    confirmBlockUser(targetUserId)  // 차단 대상 ID 전달
                    true
                }
                R.id.action_report -> {
                    showReportReasonDialog(targetUserId)  // 신고 대상 ID 전달
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    private fun handleMatchingUser() {
        Toast.makeText(this, "매칭 사용자 기능을 구현하세요.", Toast.LENGTH_SHORT).show()
    }

    private fun confirmBlockUser(blockedId: String) {
        AlertDialog.Builder(this)
            .setTitle("사용자 차단")
            .setMessage("정말로 이 사용자를 차단하시겠습니까?")
            .setPositiveButton("예") { _, _ -> blockUser(blockedId) }
            .setNegativeButton("아니오") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun blockUser(blockedId: String) {
        val blockData = BlockData(currentUserId, blockedId)
        chatService.blockUser(blockData).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ChatActivity, "User blocked successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ChatActivity, "Failed to block user", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showReportReasonDialog(reportedId: String) {
        val view = LayoutInflater.from(this).inflate(R.layout.report_dialog, null)
        val dialog = AlertDialog.Builder(this).setView(view).create()

        view.findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupReasons)
            val selectedId = radioGroup.checkedRadioButtonId
            val radioButton = view.findViewById<RadioButton>(selectedId)
            val reason = radioButton.text.toString()
            confirmReportUser(reportedId, reason)
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun confirmReportUser(reportedId: String, reason: String) {
        AlertDialog.Builder(this)
            .setTitle("사용자 신고")
            .setMessage("해당 사용자를 신고하시겠습니까?\n신고 사유: $reason")
            .setPositiveButton("예") { _, _ -> reportUser(reportedId, reason) }
            .setNegativeButton("아니오") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun reportUser(reportedId: String, reason: String) {
        val reportData = ReportData(currentUserId, reportedId, reason)
        chatService.reportUser(reportData).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@ChatActivity, "User reported successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@ChatActivity, "Failed to report user", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ChatActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showCompletionDialog(title: String, message: String) {
        val view = LayoutInflater.from(this).inflate(R.layout.activity_chat_completion, null)
        val completionMessage = view.findViewById<TextView>(R.id.completion_message)
        completionMessage.text = message
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
        view.findViewById<View>(R.id.close_button).setOnClickListener { v: View? ->
            dialog.dismiss()
        }
        dialog.show()
    }
}
