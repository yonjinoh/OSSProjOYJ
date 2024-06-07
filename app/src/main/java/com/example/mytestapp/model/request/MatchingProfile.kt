package com.example.mytestapp.model.request


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MatchingProfile(
    @SerialName("matchId")
    val matchId: String = "",
    @SerialName("userId")
    val userId: String = "",
    @SerialName("user1ID")
    val user1ID: String = "",
    @SerialName("user1Name")
    val user1Name: String = "a",
    @SerialName("user1StudentId")
    val user1StudentId: String = "a",
    @SerialName("user2ID")
    val user2ID: String = "",
    @SerialName("user2Name")
    val user2Name: String = "b",
    @SerialName("user2StudentId")
    val user2StudentId: String = "b",
    @SerialName("user3ID")
    val user3ID: String = "c",
    @SerialName("user3Name")
    val user3Name: String = "",
    @SerialName("user3StudentId")
    val user3StudentId: String = "",
    @SerialName("user4ID")
    val user4ID: String = "",
    @SerialName("user4Name")
    val user4Name: String = "",
    @SerialName("user4StudentId")
    val user4StudentId: String = "",
    @SerialName("user5ID")
    val user5ID: String = "",
    @SerialName("user5Name")
    val user5Name: String = "",
    @SerialName("user5StudentId")
    val user5StudentId: String = ""
)

