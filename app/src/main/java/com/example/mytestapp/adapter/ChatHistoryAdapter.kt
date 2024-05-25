package com.example.mytestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.model.request.ChatHistory

class ChatHistoryAdapter(
    private var chatHistoryList: List<ChatHistory>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ChatHistoryAdapter.ChatHistoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(chatHistory: ChatHistory)
    }

    inner class ChatHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userNameTextView: TextView = itemView.findViewById(R.id.text_user_name)
        val lastMessageTextView: TextView = itemView.findViewById(R.id.text_user_trait)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(chatHistoryList[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_chat_history, parent, false)
        return ChatHistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ChatHistoryViewHolder, position: Int) {
        val currentItem = chatHistoryList[position]
        holder.userNameTextView.text = currentItem.userID // 적절히 설정
        holder.lastMessageTextView.text = currentItem.lastMessage
    }

    override fun getItemCount() = chatHistoryList.size

    fun updateChatHistory(newChatHistoryList: List<ChatHistory>) {
        chatHistoryList = newChatHistoryList
        notifyDataSetChanged()
    }
}
