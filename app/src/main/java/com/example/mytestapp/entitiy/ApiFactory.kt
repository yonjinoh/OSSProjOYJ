package com.example.mytestapp.entitiy


import com.example.mytestapp.BuildConfig
import com.example.mytestapp.service.*
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

object ApiFactory {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://ec2-13-124-159-83.ap-northeast-2.compute.amazonaws.com:8000/")
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    }

    inline fun <reified T> create(): T = retrofit.create<T>(T::class.java)
}

object CriminalServicePool {
    val signupService = ApiFactory.create<SignService>()
    val loginService = ApiFactory.create<SignService>()
    val createroomService = ApiFactory.create<CreateRoomService>()
    val searchroomService = ApiFactory.create<SearchRoomService>()
    val roomlistService = ApiFactory.create<RoomlistService>()
    val enterroomService = ApiFactory.create<EnterRoomlistService>()
}


