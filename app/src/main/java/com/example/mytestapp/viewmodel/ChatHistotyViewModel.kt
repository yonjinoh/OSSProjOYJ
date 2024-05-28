package com.example.mytestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestapp.model.request.ChatHistory
import com.example.mytestapp.service.KiriService
import kotlinx.coroutines.launch

class ChatHistoryViewModel : ViewModel() {

    private val _chatHistory = MutableLiveData<List<ChatHistory>>()
    val chatHistory: LiveData<List<ChatHistory>> get() = _chatHistory

    fun fetchChatHistory() {
        viewModelScope.launch {
            try {
                val response = KiriService.chatService.getChatHistory().execute()
                if (response.isSuccessful) {
                    _chatHistory.value = response.body()
                }
            } catch (e: Exception) {
                // Handle the error
                e.printStackTrace()
            }
        }
    }

    fun onProfileClicked(chatHistory: ChatHistory) {
        // 여기에 프로파일 클릭 시 수행할 동작을 추가합니다.
        // 예를 들어, 다른 액티비티로 이동하거나 다이얼로그를 표시할 수 있습니다.
    }
}
