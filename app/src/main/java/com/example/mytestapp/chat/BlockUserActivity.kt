package com.example.mytestapp.chat

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
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

class BlockUserActivity : Activity() {
    private lateinit var chatService: ChatService
    private lateinit var currentUserId: String
    private lateinit var targetUserId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        initializeComponents()
    }

    private fun initializeComponents() {
        currentUserId = "currentUserId"
        targetUserId = "targetUserId"

        val retrofit = Retrofit.Builder()
            .baseUrl("http://yourserver.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        chatService = retrofit.create(ChatService::class.java)
    }

    fun onMoreOptionsClicked(view: View?) {
        val popup = PopupMenu(this, view)
        popup.inflate(R.menu.chat_menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_block -> {
                    confirmBlockUser(targetUserId)
                    true
                }
                else -> false
            }
        }
        popup.show()
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
        val blockId = UUID.randomUUID().toString()
        val blockData = BlockData(
            BlockID = blockId,
            UserID = currentUserId,
            BlockerID = currentUserId,
            BlockedID = blockedId
        )
        chatService.blockUser(blockData).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    showCompletionDialog("차단 완료", "해당 사용자가 차단되었습니다.")
                } else {
                    Toast.makeText(this@BlockUserActivity, "차단에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@BlockUserActivity, "에러: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun showCompletionDialog(title: String, message: String) {
        val view = layoutInflater.inflate(R.layout.activity_chat_completion, null)
        val completionMessage = view.findViewById<TextView>(R.id.completion_message)
        completionMessage.text = message
        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(false)
            .create()
        view.findViewById<View>(R.id.close_button).setOnClickListener { v: View? ->
            dialog.dismiss()
            finish()
        }
        dialog.show()
    }
}
