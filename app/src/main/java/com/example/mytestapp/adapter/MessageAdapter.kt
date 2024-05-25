package com.example.mytestapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.model.request.ChatMessage

//ChatMessage 객체의 리스트를 관리, ChatActivity에서 초기화되고, 새로운 메시지가 수신될 때마다 업데이트
class MessagesAdapter(private val messages: List<ChatMessage>) : RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    class MessageViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

    // 새로운 뷰 홀더를 생성하고, RecyclerView의 각 항목을 어떻게 표시할지 정의
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val textView = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false) as TextView
        return MessageViewHolder(textView)
    }

    // 데이터(메시지)를 뷰 홀더에 바인딩. 리스트의 각 항목에 대해 호출되어, 적절한 데이터를 해당 뷰에 설정
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.textView.text = message.content
    }

    // 어뎁터가 관리하는 데이터 항목의 수를 반환
    override fun getItemCount() = messages.size
}

// RecyclerView의 어뎁터. 채팅 메시지를 화면에 표시하기 위해 사용.
// ChatActivity에서 채팅 메시지를 리스트 형태로 보여주는 역할
// RecyclerView의 데이터와 각 항목을 어떻게 표시할지 정의