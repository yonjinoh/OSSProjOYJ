package com.example.mytestapp.service

import com.example.mytestapp.model.request.*
import com.example.mytestapp.model.response.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface SignService {

    @POST("signup/")
    fun signup(
        @Body request: signuprequest
    ): Call<signupresponse>

    @POST("login/")
    fun login(
        @Body request: loginrequest
    ): Call<loginresponse>
}

interface ProfileService{
    @POST("profile/")
    fun profile(
        @Body request: profilerequest
    ): Call<profileresponse>
}

interface RoommateService{
    @POST("roommate/")
    fun roommate(
        @Body request: roommaterequest
    ): Call<roommateresponse>
}


interface ChatService {
    @GET("messages/")
    fun getMessages(): Call<List<ChatMessage>>

    @POST("messages/")
    fun postMessage(@Body message: ChatMessage): Call<ChatMessage>

    @POST("block/")
    fun blockUser(@Body blockData: BlockData): Call<Void>

    @POST("report/")
    fun reportUser(@Body reportData: ReportData): Call<Void>

    @GET("api/chathistory/")
    fun getChatHistory(): Call<List<ChatHistory>>

}

interface MatchingService {
    @GET("matching-profiles")
    fun getMatchingProfiles(): Call<List<MatchingProfile>>
}

