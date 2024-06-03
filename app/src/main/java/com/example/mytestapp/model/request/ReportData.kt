package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ReportData(
    // 서버에서 자동으로 생성되므로 클라이언트에서 해당 필드를 보낼 필요 없음
    // @SerialName("reportId")
    // val reportId: String? = null,
    @SerialName("reporterId")
    val reporterId: String,
    @SerialName("reason")
    val reason: String,
    @SerialName("reportedId")
    val reportedId: String,
    @SerialName("timestamp")
    val timestamp: String = System.currentTimeMillis().toString()  // 기본값으로 현재 시간을 사용
)