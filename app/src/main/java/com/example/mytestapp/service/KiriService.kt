package com.example.mytestapp.service

import com.example.mytestapp.model.request.*
import com.example.mytestapp.model.response.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Path


// Retrofit 인터페이스 정의
interface SignService {
    @POST("signup/")
    fun signup(@Body request: signuprequest): Call<signupresponse>

    @POST("login/")
    fun login(@Body request: loginrequest): Call<loginresponse>
}

interface ProfileService {
    @POST("profilecreate/")
    fun profile(@Body request: profilerequest): Call<profileresponse>
}

interface RoommateService {
    @POST("userprefcreate/")
    fun roommate(@Body request: roommaterequest): Call<roommateresponse>
}

interface ChatService {
    @GET("messages/")
    fun getMessages(
        @Query("currentUserId") currentUserId: String,
        @Query("targetUserId") targetUserId: String
    ): Call<List<ChatMessage>>

    @POST("messages/")
    fun postMessage(@Body message: ChatMessage): Call<ChatMessage>

    @POST("block/")
    fun blockUser(@Body blockData: BlockData): Call<BlockResponse>

    @GET("chat-history/")
    fun getChatHistory(@Query("user_id") userId: String): Call<List<ChatHistory>>
}

interface ReportService {
    @POST("/report")
    fun reportUser(@Body reportData: ReportData): Call<ReportResponse>
}

// 사용자 정보를 조회
interface UserService {

    @GET("api/user/{user_id}/")
    fun getUserInfo(@Path("user_id") userId: String): Call<UserResponse>

}

// 매칭 관련 api
interface MatchingService {
    @GET("matching-profiles")
    fun getMatchingProfiles(@Query("user_id") userId: String): Call<List<MatchingProfile>>

    // 매칭 요청
    @POST("request-match/")
    fun requestMatch(@Body matchRequest: MatchRequest): Call<MatchResponse>

    // 매칭 수락
    @POST("api/matchaccept/")
    fun acceptMatch(@Body matchRequest: MatchRequest): Call<MatchResponse>

    // 매칭 거절
    @POST("api/matchreject/")
    fun rejectMatch(@Body matchRequest: MatchRequest): Call<MatchResponse>
}

