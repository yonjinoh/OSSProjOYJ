package com.example.mytestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class profileresponse(
    @SerialName("success")
    val success: Boolean,
)