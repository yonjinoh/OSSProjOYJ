package com.example.mytestapp.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class loginrequest(
    @SerialName("iD")
    val iD: String, // VARCHAR in SQL
    @SerialName("password")
    val password: String    // VARCHAR in SQL
)