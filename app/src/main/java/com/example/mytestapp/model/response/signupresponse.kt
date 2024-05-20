package com.example.mytestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class signupresponse(
    @SerialName("success")
    val success: Boolean,
)