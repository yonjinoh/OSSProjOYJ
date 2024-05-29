package com.example.mytestapp.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.model.request.ChatHistory
import com.example.mytestapp.chat.ChatActivity
import com.example.mytestapp.entitiy.KiriServicePool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatHistoryViewModel : ViewModel() {
    private val _chatHistories = MutableLiveData<List<ChatHistory>>()
    val chatHistories: LiveData<List<ChatHistory>> get() = _chatHistories

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        _chatHistories.value = mutableListOf()
        _errorMessage.value = ""  // 초기값을 빈 문자열로 설정
    }

    fun loadChatHistories(currentUserId: String) {
        KiriServicePool.chatService.getChatHistory(currentUserId).enqueue(object : Callback<List<ChatHistory>> {
            override fun onResponse(call: Call<List<ChatHistory>>, response: Response<List<ChatHistory>>) {
                if (response.isSuccessful) {
                    _chatHistories.value = response.body() ?: emptyList()
                    _errorMessage.value = ""  // 성공 시 에러 메시지를 빈 문자열로 설정
                } else {
                    _chatHistories.value = emptyList()
                    _errorMessage.value = "Failed to load chat histories: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<List<ChatHistory>>, t: Throwable) {
                _chatHistories.value = emptyList()
                _errorMessage.value = "Failed to load chat histories: ${t.message}"
            }
        })
    }

    fun onProfileClicked(context: Context, chatHistory: ChatHistory) {
        val intent = Intent(context, ChatActivity::class.java)
        intent.putExtra("targetUserId", chatHistory.userID2)
        intent.putExtra("targetUserName", chatHistory.userID2name)
        context.startActivity(intent)
    }
}

