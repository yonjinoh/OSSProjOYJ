package com.example.mytestapp.chat

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
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

        targetUserId = intent.getStringExtra("targetUserId") ?: "unknown"

        initializeComponents()
    }

    private fun initializeComponents() {
        currentUserId = "currentUserId"

        val retrofit = Retrofit.Builder()
            .baseUrl("http://yourserver.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        chatService = retrofit.create(ChatService::class.java)

        showReportReasonDialog(targetUserId)
    }

    private fun showReportReasonDialog(reportedId: String) {
        val view = LayoutInflater.from(this).inflate(R.layout.report_dialog, null)
        val dialog = AlertDialog.Builder(this).setView(view).create()

        view.findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            val radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupReasons)
            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(this, "신고 사유를 선택해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val radioButton = view.findViewById<RadioButton>(selectedId)
            val reason = radioButton.text.toString()
            confirmReportUser(reportedId, reason)
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.buttonCancel).setOnClickListener {
            dialog.dismiss()
            finish()
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
                    // '신고 완료'와 '해당 사용자가 신고되었습니다.'를 여기서 직접 사용
                    AlertDialog.Builder(this@ReportUserActivity)
                        .setTitle("신고 완료")
                        .setMessage("해당 사용자가 신고되었습니다.")
                        .setPositiveButton("확인") { dialog, _ ->
                            dialog.dismiss()
                            finish()
                        }
                        .show()
                } else {
                    Toast.makeText(this@ReportUserActivity, "신고에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@ReportUserActivity, "에러: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}
