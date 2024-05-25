package com.example.mytestapp.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageData(
    @SerialName("MessageID")
    val messageID: String,
    @SerialName("UserID")
    val userId: String,
    @SerialName("ReceiverID")
    val receiverID: String,
    @SerialName("SenderID")
    val senderID: String,
    @SerialName("Content")
    val content: String,
    @SerialName("Timestamp")
    val timestamp: String = System.currentTimeMillis().toString() // 기본값으로 현재 시간을 사용
)