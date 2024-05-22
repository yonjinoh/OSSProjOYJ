package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class roommaterequest(
    //option1 - mbti
    @SerialName("activity")
    val activity: String,
    @SerialName("smoking")
    val smoking: String,
    @SerialName("firstclass")
    val firstclass: String,
    @SerialName("sleepinghabit")
    val sleepinghabit: String,
    // option2
    @SerialName("sharing")
    val sharing: String,
    @SerialName("calling")
    val calling: String,
    @SerialName("hottemp")
    val hottemp: String,
    @SerialName("coldtemp")
    //option3
    val coldtemp: String,
    @SerialName("drinking")
    val drinking: String,
    @SerialName("cleaning")
    val cleaning: String,
    @SerialName("noise")
    val noise: String,
    @SerialName("sleeptime")
    val sleeptime: String,
    //option4
    @SerialName("uptime")
    val uptime: String
)
