package com.example.mytestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class loginresponse(
    @SerialName("success")
    val success: Boolean,
    @SerialName("UserID")
    val UserID : String
)