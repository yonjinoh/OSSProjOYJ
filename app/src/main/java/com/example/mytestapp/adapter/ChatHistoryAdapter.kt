package com.example.mytestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.databinding.ItemChatHistoryBinding
import com.example.mytestapp.model.request.ChatHistory
import com.example.mytestapp.viewmodel.ChatHistoryViewModel

class ChatHistoryAdapter(
    private var chatHistoryList: List<ChatHistory>,
    private val itemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ChatHistoryAdapter.ChatHistoryViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(chatHistory: ChatHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHistoryViewHolder {
        val binding = ItemChatHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatHistoryViewHolder, position: Int) {
        holder.bind(chatHistoryList[position], itemClickListener)
    }

    override fun getItemCount(): Int = chatHistoryList.size

    fun updateChatHistory(newChatHistoryList: List<ChatHistory>) {
        chatHistoryList = newChatHistoryList
        notifyDataSetChanged()
    }

    class ChatHistoryViewHolder(private val binding: ItemChatHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChatHistory, clickListener: OnItemClickListener) {
            binding.history = item
            binding.root.setOnClickListener {
                clickListener.onItemClick(item)
            }
            binding.executePendingBindings()
        }
    }
}
