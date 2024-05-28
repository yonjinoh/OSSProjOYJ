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
    val accessedTime: String = System.currentTimeMillis().toString(),
    @SerialName("accessedByName") // 백엔드에서 처리된 사용자 이름
    val accessedByName: String
)
