package com.example.mytestapp.chat

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import com.example.mytestapp.R
import com.example.mytestapp.model.request.BlockData
import com.example.mytestapp.model.response.BlockResponse
import com.example.mytestapp.service.ChatService
import com.example.mytestapp.entitiy.KiriServicePool  // KiriServicePool을 import
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.content.Context

class BlockUserActivity : Activity() {
    private lateinit var chatService: ChatService
    private lateinit var currentUserId: String
    private lateinit var targetUserId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        targetUserId = intent.getStringExtra("targetUserId") ?: "unknown"

        initializeComponents()
        confirmBlockUser(targetUserId, "해당 사용자를 차단하시겠습니까?", this::blockUser)
    }

    private fun initializeComponents() {
        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        currentUserId = sharedPreferences.getString("UserID", "unknownUserId") ?: "unknownUserId"

        // KiriServicePool에서 chatService를 가져옴
        chatService = KiriServicePool.chatService
    }

    private fun confirmBlockUser(blockedId: String, message: String, blockUser: (String)-> Unit) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.activity_chat_confirmation, null)
        val confirmButton = dialogView.findViewById<Button>(R.id.confirm_button)
        val cancelButton = dialogView.findViewById<Button>(R.id.cancel_button)
        val confirmationMessage = dialogView.findViewById<TextView>(R.id.confirmation_message)

        confirmationMessage.text = message

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

        alertDialog.show()
    }

    private fun blockUser(blockedId: String) {
        val blockId = UUID.randomUUID().toString()
        val blockData = BlockData(
//            BlockID = blockId,
//            UserID = currentUserId,
            blockerID = currentUserId,
            blockedID = blockedId
        )
        chatService.blockUser(blockData).enqueue(object : Callback<BlockResponse> {
            override fun onResponse(call: Call<BlockResponse>, response: Response<BlockResponse>) {
                if (response.isSuccessful) {
                    val blockResponse = response.body()
                    if (blockResponse != null && blockResponse.success) {
                        showCompletionDialog("해당 사용자가 차단되었습니다.")
                    } else {
                        Toast.makeText(this@BlockUserActivity, "차단에 실패했습니다", Toast.LENGTH_SHORT).show()
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
            finish()
        }

        alertDialog.show()
    }
}
