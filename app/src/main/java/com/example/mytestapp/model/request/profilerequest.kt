package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class profilerequest(
        @SerialName("user_id")
        val user_id: String,
        //gender
        @SerialName("gender")
        val gender: String,
        //option1 - mbti
        @SerialName("activity")
        val activity: String,
        @SerialName("think")
        val think: String,
        @SerialName("emotion")
        val emotion: String,
        @SerialName("pattern")
        val pattern: String,
        // option2
        @SerialName("smoking")
        val smoking: String,
        @SerialName("firstclass")
        val firstclass: String,
        @SerialName("sleepinghabit")
        val sleepinghabit: String,
        @SerialName("sharing")
        val sharing: String,
        //option3
        @SerialName("calling")
        val calling: String,
        @SerialName("hottemp")
        val hottemp: String,
        @SerialName("coldtemp")
        val coldtemp: String,
        @SerialName("drinking")
        val drinking: String,
        //option4
        @SerialName("cleaning")
        val cleaning: String,
        @SerialName("noise")
        val noise: String,
        @SerialName("sleeptime")
        val sleeptime: String,
        @SerialName("uptime")
        val uptime: String
)
