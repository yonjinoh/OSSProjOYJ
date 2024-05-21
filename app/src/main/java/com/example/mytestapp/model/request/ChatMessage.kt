package com.example.mytestapp.model.request

data class ChatMessage(
    val messageId: String,
    val userId: String,
    val receiverId: String,
    val senderId: String,
    val content: String,
    val timestamp: String
)
