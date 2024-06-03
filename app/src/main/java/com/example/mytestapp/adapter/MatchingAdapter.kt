package com.example.mytestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.databinding.ItemMatchingBinding
import com.example.mytestapp.model.request.MatchingProfile
import com.example.mytestapp.viewmodel.MatchingViewModel

class MatchingAdapter(private val viewModel: MatchingViewModel) :
    ListAdapter<MatchingProfile, MatchingAdapter.MatchingViewHolder>(MatchingDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchingViewHolder {
        val binding = ItemMatchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchingViewHolder, position: Int) {
        val profile = getItem(position)
        holder.bind(viewModel, profile, position)
    }

    class MatchingViewHolder(private val binding: ItemMatchingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MatchingViewModel, profile: MatchingProfile, position: Int) {
            binding.viewModel = viewModel
            binding.profile = profile

            // 사용자 이름 및 학번을 설정합니다.
            val userName: String
            val userStudentId: String
            when (position % 5) {
                0 -> {
                    userName = profile.user1Name
                    userStudentId = profile.user1StudentId
                }
                1 -> {
                    userName = profile.user2Name
                    userStudentId = profile.user2StudentId
                }
                2 -> {
                    userName = profile.user3Name
                    userStudentId = profile.user3StudentId
                }
                3 -> {
                    userName = profile.user4Name
                    userStudentId = profile.user4StudentId
                }
                else -> {
                    userName = profile.user5Name
                    userStudentId = profile.user5StudentId
                }
            }
            binding.userName = userName
            binding.userStudentId = userStudentId
            binding.executePendingBindings()
        }
    }
}

class MatchingDiffCallback : DiffUtil.ItemCallback<MatchingProfile>() {
    override fun areItemsTheSame(oldItem: MatchingProfile, newItem: MatchingProfile): Boolean {
        return oldItem.matchID == newItem.matchID
    }

    override fun areContentsTheSame(oldItem: MatchingProfile, newItem: MatchingProfile): Boolean {
        return oldItem == newItem
    }
}
