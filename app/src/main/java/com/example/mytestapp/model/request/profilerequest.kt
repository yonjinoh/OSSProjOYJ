package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class profilerequest(
        @SerialName("userId")
        val userId: String,

        // option1 - mbti
        @SerialName("Embti")
        val Embti: Int,
        @SerialName("Smbti")
        val Smbti: Int,
        @SerialName("Tmbti")
        val Tmbti: Int,
        @SerialName("Jmbti")
        val Jmbti: Int,

        // option2
        @SerialName("firstLesson")
        val firstLesson: Int,
        @SerialName("smoke")
        val smoke: Int,
        @SerialName("sleepHabit")
        val sleepHabit: Int,
        @SerialName("grade")
        val grade: Int,
        @SerialName("shareNeeds")
        val shareNeeds: Int,

        // option3
        @SerialName("inComm")
        val inComm: Int,
        @SerialName("heatSens")
        val heatSens: Int,
        @SerialName("coldSens")
        val coldSens: Int,
        @SerialName("drinkFreq")
        val drinkFreq: Int,

        // option4
        @SerialName("cleanliness")
        val cleanliness: Int,
        @SerialName("noiseSens")
        val noiseSens: Int,
        @SerialName("sleepSche")
        val sleepSche: Int,
        @SerialName("upSche")
        val upSche: Int
)
