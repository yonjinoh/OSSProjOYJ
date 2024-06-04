package com.example.mytestapp.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Algoresponse(
    @SerialName("success")
    val success: Boolean
)