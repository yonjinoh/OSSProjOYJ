package com.example.mytestapp.model.response

import com.example.mytestapp.model.request.MessageData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageDataResponse(
    @SerialName("messages") val messages: List<MessageData>,
    @SerialName("success") val success: Boolean,
    @SerialName("error") val error: String? = null
)

