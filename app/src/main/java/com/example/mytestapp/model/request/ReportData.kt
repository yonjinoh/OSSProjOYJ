package com.example.mytestapp.model.request

data class ReportData(
    val userId: String,
    val reportedId: String,
    val reason: String,
    val timestamp: String = System.currentTimeMillis().toString()  // 기본값으로 현재 시간을 사용
)
