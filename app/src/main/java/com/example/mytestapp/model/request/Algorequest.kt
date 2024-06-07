package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Algorequest(
    @SerialName("userId")
    val userId: String
)