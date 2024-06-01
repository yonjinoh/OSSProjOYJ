package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignupRequest(
    @SerialName("ID")
    val id: String, // VARCHAR in SQL
    @SerialName("Password")
    val password: String,   // VARCHAR in SQL
    @SerialName("Name")
    val name: String,   // VARCHAR in SQL
    @SerialName("Gender")
    val gender: String,     // VARCHAR in SQL
    @SerialName("StudentID")
    val studentId: String   // VARCHAR in SQL
)
