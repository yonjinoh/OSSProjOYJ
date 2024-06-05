package com.example.mytestapp.viewmodel

import android.content.Context
import android.content.Intent
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
    private val _matchingProfiles = MutableLiveData<MatchingProfile>() // 서버로부터 받은 매칭 결과를 담는 MutableLiveData
    val matchingProfiles: LiveData<MatchingProfile> = _matchingProfiles // 외부에서 관찰할 수 있는 LiveData

    private val matchingService: MatchingService = KiriServicePool.matchingService

    // 로그인 시 저장된 UserID를 SharedPreferences에서 불러와 데이터를 로드
    fun loadMatchingProfiles(context: Context) { // 서버에 매칭 결과를 요청하는 로직
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("UserID", null)

        // 서버로부터 받은 매칭 결과를 LiveData를 통해 MatchingFragment에 전달
        userId?.let {
            matchingService.getMatchingProfiles(it).enqueue(object : Callback<MatchingProfile> {
                override fun onResponse(call: Call<MatchingProfile>, response: Response<MatchingProfile>) {
                    if (response.isSuccessful) {
                        _matchingProfiles.value = response.body()
                    } else {
                        _matchingProfiles.value = MatchingProfile()
                    }
                }

                override fun onFailure(call: Call<MatchingProfile>, t: Throwable) {
                    _matchingProfiles.value = MatchingProfile()
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
