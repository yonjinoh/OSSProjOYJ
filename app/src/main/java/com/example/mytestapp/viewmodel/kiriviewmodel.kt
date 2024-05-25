// YourViewModel.kt
package com.example.mytestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.model.request.ChatHistory

class YourViewModel : ViewModel() {
    private val _selectedChatRoom = MutableLiveData<ChatHistory>()
    val selectedChatRoom: LiveData<ChatHistory> get() = _selectedChatRoom

    fun onRoomClicked(chatHistory: ChatHistory) {
        _selectedChatRoom.value = chatHistory
    }
}
