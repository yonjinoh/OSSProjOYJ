package com.example.mytestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.databinding.ChatMessageItemBinding
import com.example.mytestapp.databinding.ChatMessageItemMatchRequestBinding
import com.example.mytestapp.model.response.ChatMessage

class ChatAdapter(
    private val currentUserId: String,
    private var messages: List<ChatMessage>,
    private val onAcceptClick: (ChatMessage) -> Unit,
    private val onRejectClick: (ChatMessage) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_MESSAGE = 1
        private const val VIEW_TYPE_MATCH_REQUEST = 2
        private const val MATCH_REQUEST_KEYWORD = "매칭을 요청했습니다" // 매칭 요청 메시지를 구분하는 키워드
    }

    inner class ChatViewHolder(private val binding: ChatMessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.chatMessage = message
            binding.executePendingBindings()

            if (message.senderID == currentUserId) {
                binding.layoutSentMessage.visibility = View.VISIBLE
                binding.layoutReceivedMessage.visibility = View.GONE
            } else {
                binding.layoutReceivedMessage.visibility = View.VISIBLE
                binding.layoutSentMessage.visibility = View.GONE
            }
        }
    }

    inner class MatchRequestViewHolder(private val binding: ChatMessageItemMatchRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            // A가 보낸 메시지에는 버튼을 비활성화
            if (message.senderID == currentUserId) {
                binding.acceptButton.visibility = View.GONE
                binding.rejectButton.visibility = View.GONE
            } else {
                binding.acceptButton.visibility = View.VISIBLE
                binding.rejectButton.visibility = View.VISIBLE
                binding.acceptButton.setOnClickListener { onAcceptClick(message) }
                binding.rejectButton.setOnClickListener { onRejectClick(message) }
            }
        }
    }




    override fun getItemViewType(position: Int): Int {
        return if (messages[position].content.contains(MATCH_REQUEST_KEYWORD)) {
            VIEW_TYPE_MATCH_REQUEST
        } else {
            VIEW_TYPE_MESSAGE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_MATCH_REQUEST -> {
                val binding = ChatMessageItemMatchRequestBinding.inflate(layoutInflater, parent, false)
                MatchRequestViewHolder(binding)
            }
            VIEW_TYPE_MESSAGE -> {
                val binding = ChatMessageItemBinding.inflate(layoutInflater, parent, false)
                ChatViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_MATCH_REQUEST -> (holder as MatchRequestViewHolder).bind(messages[position])
            VIEW_TYPE_MESSAGE -> (holder as ChatViewHolder).bind(messages[position])
        }
    }

    override fun getItemCount(): Int = messages.size

    fun submitList(newMessages: List<ChatMessage>) {
        messages = newMessages
        notifyDataSetChanged()
    }
}