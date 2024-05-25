package com.example.mytestapp.matching

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.adapters.MatchingAdapter
import com.example.mytestapp.model.request.MatchingProfile
import com.example.mytestapp.service.MatchingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MatchingActivity : AppCompatActivity() {

    private lateinit var matchingRecyclerView: RecyclerView
    private lateinit var matchingAdapter: MatchingAdapter
    private lateinit var matchingService: MatchingService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_matching)

        matchingRecyclerView = findViewById(R.id.rv_matching_list)

        matchingAdapter = MatchingAdapter(emptyList(), object : MatchingAdapter.OnItemClickListener {
            override fun onItemClick(profile: MatchingProfile) {
                // 클릭 이벤트 처리
            }
        })
        matchingRecyclerView.layoutManager = LinearLayoutManager(this)
        matchingRecyclerView.adapter = matchingAdapter

        // Retrofit 설정
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your-django-server.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        matchingService = retrofit.create(MatchingService::class.java)

        loadMatchingProfiles()
    }

    private fun loadMatchingProfiles() {
        matchingService.getMatchingProfiles().enqueue(object : Callback<List<MatchingProfile>> {
            override fun onResponse(call: Call<List<MatchingProfile>>, response: Response<List<MatchingProfile>>) {
                if (response.isSuccessful) {
                    val matchingProfileList = response.body() ?: emptyList()
                    matchingAdapter.updateProfiles(matchingProfileList)
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<List<MatchingProfile>>, t: Throwable) {
                // Handle the failure
            }
        })
    }
}
