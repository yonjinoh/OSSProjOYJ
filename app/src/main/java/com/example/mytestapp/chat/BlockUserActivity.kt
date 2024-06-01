package com.example.mytestapp.chat

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import com.example.mytestapp.R
import com.example.mytestapp.model.request.BlockData
import com.example.mytestapp.service.ChatService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID
import com.example.mytestapp.model.response.BlockResponse
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.content.Context

class BlockUserActivity : Activity() {
    private lateinit var chatService: ChatService
    private lateinit var currentUserId: String
    private lateinit var targetUserId: String

    // targetUserId를 Intent로부터 받아오기
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        targetUserId = intent.getStringExtra("targetUserId") ?: "unknown"

        initializeComponents()
        confirmBlockUser(targetUserId)
    }

    // SharedPreferences에서 현재 사용자 아이디 읽어오기
    private fun initializeComponents() {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        currentUserId = sharedPreferences.getString("userId", "unknownUserId") ?: "unknownUserId"

        val retrofit = Retrofit.Builder()
            .baseUrl("http://yourserver.com/api/")  //실제 사용 서버 입력해야됨
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        chatService = retrofit.create(ChatService::class.java)
    }

    // 사용자를 차단할지 묻는 다이얼로그를 표시하고, '예'를 선택하면 blockUser 메서드를 호출
    private fun confirmBlockUser(blockedId: String) {
        // 커스텀 다이얼로그 레이아웃 로드
        val dialogView = LayoutInflater.from(this).inflate(R.layout.activity_chat_confirmation, null)
        val confirmButton = dialogView.findViewById<Button>(R.id.confirm_button)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancel_button)

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        confirmButton.setOnClickListener {
            blockUser(blockedId)
            alertDialog.dismiss()
        }

        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()  // 다이얼로그 표시
    }

    // blockUser 메서드에서 서버에 차단 요청을 보내고, 성공 시 차단 완료 메시지를 표시
    private fun blockUser(blockedId: String) {
        val blockId = UUID.randomUUID().toString()
        val blockData = BlockData(
            BlockID = blockId,
            UserID = currentUserId,
            BlockerID = currentUserId,
            BlockedID = blockedId
        )
        chatService.blockUser(blockData).enqueue(object : Callback<BlockResponse> {
            override fun onResponse(call: Call<BlockResponse>, response: Response<BlockResponse>) {
                if (response.isSuccessful) {
                    val blockResponse = response.body()
                    if (blockResponse != null && blockResponse.success) {
                        showCompletionDialog("해당 사용자가 차단되었습니다.")
                    } else {
                        Toast.makeText(this@BlockUserActivity, "차단에 실패했습니다: ${blockResponse?.error}", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@BlockUserActivity, "차단에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<BlockResponse>, t: Throwable) {
                Toast.makeText(this@BlockUserActivity, "에러: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    // 차단 완료 다이얼로그를 표시하는 함수
    private fun showCompletionDialog(message: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.activity_chat_completion, null)
        val completionMessage = dialogView.findViewById<TextView>(R.id.completion_message)
        val closeButton = dialogView.findViewById<Button>(R.id.close_button)

        completionMessage.text = message

        val alertDialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        closeButton.setOnClickListener {
            alertDialog.dismiss()
            finish() // 액티비티 종료
        }

        alertDialog.show()
    }
}
