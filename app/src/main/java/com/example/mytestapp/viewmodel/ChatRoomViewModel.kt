package com.example.mytestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestapp.model.request.ChatMessage

class ChatRoomViewModel : ViewModel() {
    private val _chatMessages = MutableLiveData<List<ChatMessage>>()
    val chatMessages: LiveData<List<ChatMessage>> get() = _chatMessages

    init {
        _chatMessages.value = mutableListOf()
    }

    fun addMessage(message: ChatMessage) {
        _chatMessages.value = _chatMessages.value?.plus(message)
    }
}
