package com.example.mytestapp.chat

data class BlockData(
    val userId: String,
    val blockedId: String,
    val timestamp: String = System.currentTimeMillis().toString()  // 기본값으로 현재 시간을 사용
)
