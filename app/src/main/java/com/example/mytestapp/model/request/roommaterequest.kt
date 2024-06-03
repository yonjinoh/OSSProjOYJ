package com.example.mytestapp.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class roommaterequest(
    // 사용자 ID
    @SerialName("UuserID")
    val UuserID: String,
    // MBTI 옵션
    @SerialName("UEmbti")
    val UEmbti: Int,

    // 기타 옵션
    @SerialName("UfirstLesson")
    val UfirstLesson: Int,
    @SerialName("Usmoke")
    val Usmoke: Int,
    @SerialName("UsleepHabit")
    val UsleepHabit: Int,
    @SerialName("Ugrade")
    val Ugrade: Int,
    @SerialName("UshareNeeds")
    val UshareNeeds: Int,
    @SerialName("UinComm")
    val UinComm: Int,
    @SerialName("UheatSens")
    val UheatSens: Int,
    @SerialName("UcoldSens")
    val UcoldSens: Int,
    @SerialName("UdrinkFreq")
    val UdrinkFreq: Int,
    @SerialName("Ucleanliness")
    val Ucleanliness: Int,
    @SerialName("UnoiseSens")
    val UnoiseSens: Int,
    @SerialName("UsleepSche")
    val UsleepSche: Int,
    @SerialName("UupSche")
    val UupSche: Int
)