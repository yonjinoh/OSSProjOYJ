package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BlockData(
    @SerialName("blockerID")
    val blockerID: String,
    @SerialName("blockedID")
    val blockedID: String
)
