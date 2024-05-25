package com.example.mytestapp.chat

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.adapters.ChatHistoryAdapter
import com.example.mytestapp.model.request.ChatHistory
import com.example.mytestapp.service.ChatService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ChatHistoryFragment : Fragment() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatHistoryAdapter
    private lateinit var chatService: ChatService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false) // 올바른 레이아웃 파일 참조
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatRecyclerView = view.findViewById(R.id.rv_chat_list)

        chatAdapter = ChatHistoryAdapter(emptyList(), object : ChatHistoryAdapter.OnItemClickListener {
            override fun onItemClick(chatHistory: ChatHistory) {
                // 채팅 방 클릭 시 ChatActivity 시작
                val intent = Intent(requireContext(), ChatActivity::class.java)
                intent.putExtra("targetUserId", chatHistory.userID)
                intent.putExtra("targetUserName", "User Name") // userName을 적절히 설정
                startActivity(intent)
            }
        })

        chatRecyclerView.layoutManager = LinearLayoutManager(context)
        chatRecyclerView.adapter = chatAdapter

        // Retrofit 설정
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your-django-server.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        chatService = retrofit.create(ChatService::class.java)

        loadChatHistory()
    }

    private fun loadChatHistory() {
        chatService.getChatHistory().enqueue(object : Callback<List<ChatHistory>> {
            override fun onResponse(call: Call<List<ChatHistory>>, response: Response<List<ChatHistory>>) {
                if (response.isSuccessful) {
                    val chatHistoryList = response.body() ?: emptyList()
                    chatAdapter.updateChatHistory(chatHistoryList)
                } else {
                    // Handle the error
                }
            }

            override fun onFailure(call: Call<List<ChatHistory>>, t: Throwable) {
                // Handle the failure
            }
        })
    }
}
