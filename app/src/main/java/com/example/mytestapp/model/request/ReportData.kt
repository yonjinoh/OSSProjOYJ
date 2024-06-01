package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReportData(
    @SerialName("reportId")
    val reportId: String? = null,
    @SerialName("reporterId")
    val reporterId: String,
    @SerialName("reason")
    val reason: String,
    @SerialName("reportedId")
    val reportedId: String,
    @SerialName("timestamp")
    val timestamp: String = System.currentTimeMillis().toString()  // 기본값으로 현재 시간을 사용
)