package com.example.mytestapp.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.R
import com.example.mytestapp.chat.ChatActivity
import com.example.mytestapp.model.request.MatchingProfile
import com.example.mytestapp.service.MatchingService
import com.example.mytestapp.entitiy.KiriServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchingViewModel : ViewModel() {
    // LiveData 객체
    private val _matchingProfiles = MutableLiveData<List<MatchingProfile>>() // 서버로부터 받은 매칭 결과를 담는 MutableLiveData
    val matchingProfiles: LiveData<List<MatchingProfile>> = _matchingProfiles // 외부에서 관찰할 수 있는 LiveData

    private val matchingService: MatchingService = KiriServicePool.matchingService

    // 로그인 시 저장된 UserID를 SharedPreferences에서 불러와 데이터를 로드
    fun loadMatchingProfiles(context: Context) { // 서버에 매칭 결과를 요청하는 로직
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("UserID", null)

        // 서버로부터 받은 매칭 결과를 LiveData를 통해 MatchingFragment에 전달
        userId?.let {
            matchingService.getMatchingProfiles(it).enqueue(object : Callback<List<MatchingProfile>> {
                override fun onResponse(call: Call<List<MatchingProfile>>, response: Response<List<MatchingProfile>>) {
                    if (response.isSuccessful) {
                        val profiles = response.body()
                        // 매칭 프로필을 5명의 사용자 프로필로 변환하여 MutableLiveData에 설정
                        val userProfiles = mutableListOf<MatchingProfile>()
                        profiles?.forEach { profile ->
                            userProfiles.add(MatchingProfile(profile.matchId, profile.userId, profile.user1ID, profile.user1Name, profile.user1StudentId))
                            userProfiles.add(MatchingProfile(profile.matchId, profile.userId, profile.user2ID, profile.user2Name, profile.user2StudentId))
                            userProfiles.add(MatchingProfile(profile.matchId, profile.userId, profile.user3ID, profile.user3Name, profile.user3StudentId))
                            userProfiles.add(MatchingProfile(profile.matchId, profile.userId, profile.user4ID, profile.user4Name, profile.user4StudentId))
                            userProfiles.add(MatchingProfile(profile.matchId, profile.userId, profile.user5ID, profile.user5Name, profile.user5StudentId))
                        }
                        _matchingProfiles.value = userProfiles
                    } else {
                        Toast.makeText(context, "매칭 결과를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                        _matchingProfiles.value = emptyList()
                    }
                }

                override fun onFailure(call: Call<List<MatchingProfile>>, t: Throwable) {
                    Log.e("MatchingViewModel", "Failed to load matching profiles", t)
                    Toast.makeText(context, "매칭 결과를 불러오는데 실패했습니다.", Toast.LENGTH_SHORT).show()
                    _matchingProfiles.value = emptyList()
                }
            })
        }
    }

    // 프로필을 클릭했을 때 ChatActivity로 이동하는 로직
    fun onProfileClicked(view: View, profile: MatchingProfile) {
        val context = view.context
        val intent = Intent(context, ChatActivity::class.java).apply {
            putExtra("targetUserId", profile.userId)
            putExtra("targetUserName", profile.userId) // 이름 대신 userID를 사용, 필요에 따라 수정
        }
        context.startActivity(intent)
    }

    // 메뉴 버튼을 클릭했을 때의 로직
    fun onMenuButtonClicked(view: View) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.inflate(R.menu.item_menu)
        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.nav_home -> {
                    // 홈으로 이동하는 로직
                    true
                }
                R.id.nav_roomate -> {
                    // 룸메이트 추천으로 이동하는 로직
                    true
                }
                R.id.nav_chat -> {
                    // 채팅으로 이동하는 로직
                    true
                }
                R.id.nav_mypage -> {
                    // 마이페이지로 이동하는 로직
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}
