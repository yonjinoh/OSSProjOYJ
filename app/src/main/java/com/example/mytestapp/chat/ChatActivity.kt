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
import com.example.mytestapp.model.request.MessageData
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.JSONObject

class ChatActivity : AppCompatActivity() {
    private lateinit var messages: MutableList<ChatMessage>
    private lateinit var adapter: MessagesAdapter
    private lateinit var messageInput: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var sendButton: Button
    private lateinit var imageMenu: ImageView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var currentUserId: String
    private lateinit var targetUserId: String
    private lateinit var targetUserName: String
    private lateinit var webSocket: WebSocket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat) // 레이아웃 설정

        // Intent로부터 상대방의 이름과 ID를 가져옴
        targetUserId = intent.getStringExtra("targetUserId") ?: "Unknown User"
        targetUserName = intent.getStringExtra("targetUserName") ?: "Unknown User"

        initializeComponents()
        setupButtonListeners()
        initializeWebSocket()
    }

    private fun initializeComponents() {
        currentUserId = "currentUserId" // 현재 사용자 ID 설정

        messageInput = findViewById(R.id.chat_input) // 메시지 입력 필드 찾기
        sendButton = findViewById(R.id.message_send) // 전송 버튼 찾기
        recyclerView = findViewById(R.id.chat_recyclerView)  // RecycleerView 찾기
        imageMenu = findViewById(R.id.imageMenu)
        drawerLayout = findViewById(R.id.drawerLayout)

        val textTitle = findViewById<TextView>(R.id.textTitle) // 채팅방 제목 TextView 찾기
        textTitle.text = targetUserName // 상대방의 이름으로 제목 설정

        messages = mutableListOf() // 메시지 리스트 초기화
        adapter = MessagesAdapter(messages)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    // 버튼 리스너 설정 메소드 (메시지가 있으면 전송 후 입력 필드 초기화 / 없으면 메시지 입력 문구 출력)
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

        // 메뉴 버튼을 위한 method
        imageMenu.setOnClickListener {
            val popupMenu = PopupMenu(this, imageMenu) // PopupMenu 객체 생성
            popupMenu.inflate(R.menu.chat_menu) // 메뉴 리소스를 팽창시킴
            popupMenu.setOnMenuItemClickListener { item: MenuItem -> // MenuItem 클릭 리스너 설정
                when (item.itemId) {
                    R.id.action_report -> {
                        val intent = Intent(this, ReportUserActivity::class.java) // ReportUserActivity로의 Intent 생성
                        intent.putExtra("targetUserId", targetUserId) // Intent에 targetUserId 추가
                        startActivity(intent) // Activity 시작
                        true
                    }
                    R.id.action_block -> {
                        val intent = Intent(this, BlockUserActivity::class.java) // BlockUserActivity로의 Intent 생성
                        intent.putExtra("targetUserId", targetUserId) // Intent에 targetUserId 추가
                        startActivity(intent) // Activity 시작
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show() // PopupMenu 표시
        }
    }

    private fun initializeWebSocket() {
        val client = OkHttpClient()
        val request = Request.Builder().url("ws://yourserver.com/socket").build()
        webSocket = client.newWebSocket(request, object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: okhttp3.Response) {
                // 웹소켓 연결 성공
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                runOnUiThread {
                    // 서버로부터 받은 메시지 처리
                    val jsonObject = JSONObject(text) // json 객체로 변환
                    val chatMessage = ChatMessage(
                        jsonObject.getString("messageID"),
                        jsonObject.getString("senderID"),
                        jsonObject.getString("receiverID"),
                        jsonObject.getString("userID"),
                        jsonObject.getString("content"),
                        jsonObject.getString("timestamp")
                    )
                    messages.add(chatMessage) // 메시지 리스트에 추가
                    adapter.notifyItemInserted(messages.size - 1) // 변경된 부분만 업데이트
                    recyclerView.scrollToPosition(messages.size - 1)
                }
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                // 바이너리 메시지 처리 (필요시 구현)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: okhttp3.Response?) {
                runOnUiThread {
                    Toast.makeText(this@ChatActivity, "웹소켓 연결 실패: ${t.message}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                webSocket.close(1000, null)
                runOnUiThread {
                    Toast.makeText(this@ChatActivity, "웹소켓 연결 종료: $reason", Toast.LENGTH_LONG).show()
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                // 웹소켓 연결이 완전히 종료된 경우
            }
        })
    }

    // 메시지 전송 메소드 (json을 변환 후 websocket을 통해 메시지 전송)
    private fun sendMessage(messageText: String) {
        val messageId = "" // ID는 서버에서 생성
        val timestamp = System.currentTimeMillis().toString() // 현재 시간으로 타임스탬프 생성

        val messageData = MessageData( // MessageData 객체 생성
            messageID = messageId,
            userId = currentUserId,
            receiverID = targetUserId,
            senderID = currentUserId,
            content = messageText,
            timestamp = timestamp
        )

        val jsonObject = JSONObject().apply {
            put("MessageID", messageData.messageID)
            put("UserID", messageData.userId)
            put("ReceiverID", messageData.receiverID)
            put("SenderID", messageData.senderID)
            put("Content", messageData.content)
            put("Timestamp", messageData.timestamp)
        }
        webSocket.send(jsonObject.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocket.close(1000, "앱 종료")
    }
}
