package com.example.mytestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.databinding.ChatMessageItemBinding
import com.example.mytestapp.databinding.ChatMessageItemMatchRequestBinding
import com.example.mytestapp.model.request.ChatMessage

class ChatAdapter(
    private val currentUserId: String,
    private var messages: List<ChatMessage>,
    private val onAcceptClick: (ChatMessage) -> Unit,
    private val onRejectClick: (ChatMessage) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MESSAGE = 1
        private const val VIEW_TYPE_MATCH_REQUEST = 2
    }

    fun submitList(newMessages: List<ChatMessage>) {
        messages = newMessages
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].content.contains("매칭을 수락하시겠습니까?")) {
            VIEW_TYPE_MATCH_REQUEST
        } else {
            VIEW_TYPE_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_MATCH_REQUEST) {
            val binding = ChatMessageItemMatchRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MatchRequestViewHolder(binding)
        } else {
            val binding = ChatMessageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            MessageViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messages[position]
        if (holder is MatchRequestViewHolder) {
            holder.bind(message, currentUserId, onAcceptClick, onRejectClick)
        } else if (holder is MessageViewHolder) {
            holder.bind(message, currentUserId)
        }
    }

    override fun getItemCount(): Int = messages.size

    class MessageViewHolder(private val binding: ChatMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chatMessage: ChatMessage, currentUserId: String) {
            binding.chatMessage = chatMessage

            if (chatMessage.senderId == currentUserId) {
                binding.layoutSentMessage.visibility = View.VISIBLE
                binding.layoutReceivedMessage.visibility = View.GONE
            } else {
                binding.layoutSentMessage.visibility = View.GONE
                binding.layoutReceivedMessage.visibility = View.VISIBLE
            }

            binding.executePendingBindings()
        }
    }

    class MatchRequestViewHolder(private val binding: ChatMessageItemMatchRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            chatMessage: ChatMessage,
            currentUserId: String,
            onAcceptClick: (ChatMessage) -> Unit,
            onRejectClick: (ChatMessage) -> Unit
        ) {
            binding.acceptButton.setOnClickListener { onAcceptClick(chatMessage) }
            binding.rejectButton.setOnClickListener { onRejectClick(chatMessage) }

            if (chatMessage.senderId == currentUserId) {
                binding.matchRequestChatTextBackground.visibility = View.VISIBLE
                binding.layoutReceivedMessage.visibility = View.GONE
            } else {
                binding.matchRequestChatTextBackground.visibility = View.GONE
                binding.layoutReceivedMessage.visibility = View.VISIBLE
            }

            binding.executePendingBindings()
        }
    }
}
