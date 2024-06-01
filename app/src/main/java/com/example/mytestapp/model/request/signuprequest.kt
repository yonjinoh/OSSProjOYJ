package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class signuprequest(
    @SerialName("iD")
    val iD: String, // VARCHAR in SQL
    @SerialName("password")
    val password: String,   // VARCHAR in SQL
    @SerialName("name")
    val name: String,   // VARCHAR in SQL // VARCHAR in SQL
    @SerialName("studentID")
    val studentID: String,
    @SerialName("gender")
    val gender: String// VARCHAR in SQL
)