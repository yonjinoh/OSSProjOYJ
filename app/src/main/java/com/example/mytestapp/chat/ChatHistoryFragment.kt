package com.example.mytestapp.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.model.request.ChatHistory
import com.example.mytestapp.adapters.ChatHistoryAdapter
import com.example.mytestapp.viewmodel.ChatHistoryViewModel

class ChatHistoryFragment : Fragment() {

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var chatAdapter: ChatHistoryAdapter
    private val chatHistoryViewModel: ChatHistoryViewModel by viewModels()

    companion object {
        fun newInstance(): ChatHistoryFragment {
            return ChatHistoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chathistory, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatRecyclerView = view.findViewById(R.id.rv_chat_list)

        chatAdapter = ChatHistoryAdapter(chatHistoryViewModel, object : ChatHistoryAdapter.OnItemClickListener {
            override fun onItemClick(chatHistory: ChatHistory) {
                // 채팅 방 클릭 시 ChatActivity 시작
                val intent = Intent(requireContext(), ChatActivity::class.java)
                intent.putExtra("targetUserId", chatHistory.userID2)
                intent.putExtra("targetUserName", chatHistory.userID2name)
                startActivity(intent)
            }
        })

        chatRecyclerView.layoutManager = LinearLayoutManager(context)
        chatRecyclerView.adapter = chatAdapter

        // 데이터 로드
        val sharedPreferences = requireContext().getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val currentUserId = sharedPreferences.getString("userId", "") ?: ""
        chatHistoryViewModel.loadChatHistories(currentUserId)

        chatHistoryViewModel.chatHistories.observe(viewLifecycleOwner) { chatHistories ->
            chatAdapter.submitList(chatHistories)
        }

        // 뒤로가기 버튼 설정
        val btnBack: Button = view.findViewById(R.id.btn_back)
        btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}
