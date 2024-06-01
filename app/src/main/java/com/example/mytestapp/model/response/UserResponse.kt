package com.example.mytestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    @SerialName("userID")
    val userID: String,
    @SerialName("username")
    val username: String
)