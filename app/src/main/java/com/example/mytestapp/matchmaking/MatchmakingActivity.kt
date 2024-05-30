package com.example.mytestapp.matchmaking

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mytestapp.R
import com.example.mytestapp.entitiy.KiriServicePool
import com.example.mytestapp.model.request.MatchRequest
import com.example.mytestapp.model.response.MatchResponse
import com.example.mytestapp.model.response.UserResponse
import com.example.mytestapp.websocket.WebSocketManager
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchmakingActivity : AppCompatActivity() {

    private var targetUserId: String = ""
    private var currentUserId: String = ""
    private lateinit var webSocketManager: WebSocketManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_confirmation)

        // 인텐트에서 전달된 사용자 ID를 가져옴
        targetUserId = intent.getStringExtra("targetUserId") ?: ""
        currentUserId = intent.getStringExtra("currentUserId") ?: ""

        if (targetUserId.isNotEmpty()) {
            fetchUserName(targetUserId) // 사용자 ID가 유효한 경우 사용자 이름을 조회
        } else {
            showError("사용자 ID를 가져올 수 없습니다.") // 사용자 ID가 유효하지 않은 경우 에러 메시지 표시
        }

        // WebSocketManager 초기화 및 연결
        webSocketManager = WebSocketManager(
            onMessageReceived = {},
            onConnectionFailed = { error -> runOnUiThread {
                Toast.makeText(this, "WebSocket 연결 실패: $error", Toast.LENGTH_LONG).show() // 수정
            }}
        )
        webSocketManager.connect()

        val confirmButton = findViewById<Button>(R.id.confirm_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        confirmButton.setOnClickListener {
            performMatching() // 매칭을 수행하는 메서드 호출
        }

        cancelButton.setOnClickListener {
            finish()  // 액티비티 종료
        }
    }

    // 채팅 중인 상대방의 이름을 조회
    private fun fetchUserName(userId: String) { // 수정: Int에서 String으로 변경
        KiriServicePool.userService.getUserInfo(userId).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    val userName = response.body()?.username ?: "알 수 없는 사용자"
                    updateUI(userName) // 조회한 사용자 이름으로 UI 업데이트
                } else {
                    showError("사용자 정보를 가져올 수 없습니다.") // 사용자 정보를 가져오지 못한 경우 에러 메시지 표시
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                showError("네트워크 오류: ${t.message}") // 네트워크 오류가 발생한 경우 에러 메시지 표시
            }
        })
    }

    // 조회한 사용자 이름으로 UI를 업데이트
    private fun updateUI(userName: String) {
        val confirmationMessage = findViewById<TextView>(R.id.confirmation_message)
        confirmationMessage.text = "$userName 사용자와 매칭을 진행하시겠습니까?"
    }

    // 에러 메시지를 토스트로 표시하는 메서드
    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // 매칭을 수행하는 메서드
    private fun performMatching() {
        // 현재 사용자의 ID를 포함하는 매칭 요청 객체 생성
        val matchRequest = MatchRequest(
            userId = currentUserId
        )
        // 매칭 요청을 서버에 보냄
        KiriServicePool.matchingService.requestMatch(matchRequest).enqueue(object : Callback<MatchResponse> {
            override fun onResponse(call: Call<MatchResponse>, response: Response<MatchResponse>) {
                if (response.isSuccessful) {
                    val matchResponse = response.body()
                    if (matchResponse?.success == true) {
                        findViewById<TextView>(R.id.confirmation_message).text = "해당 사용자에게 매칭을 신청했습니다. 상대가 매칭을 수락하면 매칭이 완료됩니다."
                        sendMatchRequestToTarget()
                    } else {
                        showError(matchResponse?.message ?: "매칭 신청에 실패했습니다.")
                    }
                } else {
                    showError("매칭 신청에 실패했습니다.")
                }
            }

            override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                showError("네트워크 오류: ${t.message}")
            }
        })
    }

    private fun sendMatchRequestToTarget() {
        val messageData = JSONObject().apply {
            put("type", "match_request")
            put("senderId", currentUserId)
            put("receiverId", targetUserId)
            put("content", "상대가 매칭을 신청했습니다. 매칭을 수락하시겠습니까?\n")
        }
        webSocketManager.sendMessage(messageData.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        webSocketManager.disconnect()
    }
}