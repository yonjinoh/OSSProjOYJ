package com.example.mytestapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.databinding.ChatMessageItemBinding
import com.example.mytestapp.model.request.ChatMessage

class ChatAdapter(private var messages: List<ChatMessage>, private val currentUserId: String) : RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val binding = ChatMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messages[position], currentUserId)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    fun submitList(newMessages: List<ChatMessage>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    class MessageViewHolder(private val binding: ChatMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage, currentUserId: String) {
            binding.chatMessage = message

            if (message.senderId == currentUserId) {
                binding.layoutSentMessage.visibility = View.VISIBLE
                binding.layoutReceivedMessage.visibility = View.GONE
            } else {
                binding.layoutSentMessage.visibility = View.GONE
                binding.layoutReceivedMessage.visibility = View.VISIBLE
            }

            binding.executePendingBindings()
        }
    }
}
