package com.example.mytestapp.chat

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.example.mytestapp.R
import com.example.mytestapp.model.request.ReportData
import com.example.mytestapp.service.ChatService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

class ReportUserActivity : Activity() {
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
                R.id.action_report -> {
                    showReportReasonDialog(targetUserId)
                    true
                }
                else -> false
            }
        }
        popup.show()
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
        val reportId = UUID.randomUUID().toString()
        val reportData = ReportData(
            ReportID = reportId,
            UserID = currentUserId,
            Reason = reason,
            ReporterID = currentUserId,
            ReportedID = reportedId
        )
        chatService.reportUser(reportData).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    showCompletionDialog("신고 완료", "해당 사용자가 신고되었습니다.")
                } else {
                    Toast.makeText(this@ReportUserActivity, "신고에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ReportUserActivity, "에러: ${t.message}", Toast.LENGTH_LONG).show()
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
