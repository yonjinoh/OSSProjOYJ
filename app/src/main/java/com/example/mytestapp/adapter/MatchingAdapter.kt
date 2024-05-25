package com.example.mytestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.model.request.MatchingProfile

class MatchingAdapter(
    private var profiles: List<MatchingProfile>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<MatchingAdapter.ProfileViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(profile: MatchingProfile)
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userImageView: ImageView = itemView.findViewById(R.id.image_user)
        val userNameTextView: TextView = itemView.findViewById(R.id.text_user_name)
        val matchScoreTextView: TextView = itemView.findViewById(R.id.text_user_trait)

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(profiles[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_matching, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = profiles[position]
        // 프로필 데이터에 맞게 설정
        holder.userNameTextView.text = currentItem.userID // 적절히 설정
        holder.matchScoreTextView.text = currentItem.matchScore
        // holder.userImageView.setImageResource(currentItem.profileImage) // 프로필 이미지 설정 (필요시)
    }

    override fun getItemCount() = profiles.size

    fun updateProfiles(newProfiles: List<MatchingProfile>) {
        profiles = newProfiles
        notifyDataSetChanged()
    }
}
