package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatHistory(
    @SerialName("HistoryID")
    val historyID: String,
    @SerialName("UserID")
    val userID: String,
    @SerialName("MessageID")
    val messageID: String,
    @SerialName("AccessedBy")
    val accessedBy: String,
    @SerialName("lastMessage")
    val lastMessage: String,
    @SerialName("hasNewMessages")
    val hasNewMessages: Boolean,
    @SerialName("AccessedTime")
    val accessedTime: String = System.currentTimeMillis().toString(), // 기본값으로 현재 시간을 사용
    val userName: String = "" //signuprequest에서 불러와서 정의함
)
