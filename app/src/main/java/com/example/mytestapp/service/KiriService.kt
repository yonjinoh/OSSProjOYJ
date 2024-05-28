package com.example.mytestapp.service

import com.example.mytestapp.model.request.*
import com.example.mytestapp.model.response.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Retrofit 인터페이스 정의
interface SignService {
    @POST("signup/")
    fun signup(@Body request: signuprequest): Call<signupresponse>

    @POST("login/")
    fun login(@Body request: loginrequest): Call<loginresponse>
}

interface ProfileService {
    @POST("profile/")
    fun profile(@Body request: profilerequest): Call<profileresponse>
}

interface RoommateService {
    @POST("roommate/")
    fun roommate(@Body request: roommaterequest): Call<roommateresponse>
}

interface ChatService {
    @GET("messages/")
    fun getMessages(): Call<List<ChatMessage>>

    @POST("messages/")
    fun postMessage(@Body message: ChatMessage): Call<ChatMessage>

    @POST("block/")
    fun blockUser(@Body blockData: BlockData): Call<BlockResponse>

    @POST("report/")
    fun reportUser(@Body reportData: ReportData): Call<ReportResponse>

    @GET("chat/history/")
    fun getChatHistory(): Call<List<ChatHistory>>

    @GET("user/{userId}")
    fun getUser(@Path("userId") userId: String): Call<signuprequest>
}

interface MatchingService {
    @GET("matching-profiles")
    fun getMatchingProfiles(): Call<List<MatchingProfile>>
}

// Retrofit 인스턴스를 생성하고 서비스 인터페이스를 제공하는 객체 정의
object KiriService {
    private const val BASE_URL = "http://your-django-server.com/" // 실제 서버 주소로 변경

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val chatService: ChatService by lazy {
        retrofit.create(ChatService::class.java)
    }

    val matchingService: MatchingService by lazy {
        retrofit.create(MatchingService::class.java)
    }
}
