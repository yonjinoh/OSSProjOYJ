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
        holder.bind(viewModel, profile)
    }

    class MatchingViewHolder(private val binding: ItemMatchingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: MatchingViewModel, profile: MatchingProfile) {
            binding.viewModel = viewModel
            binding.profile = profile

            // 사용자 이름 및 학번을 설정합니다.
            binding.userName = profile.user1Name
            binding.userStudentId = profile.user1StudentId

            binding.executePendingBindings()
        }
    }
}

class MatchingDiffCallback : DiffUtil.ItemCallback<MatchingProfile>() {
    override fun areItemsTheSame(oldItem: MatchingProfile, newItem: MatchingProfile): Boolean {
        return oldItem.matchId == newItem.matchId
    }

    override fun areContentsTheSame(oldItem: MatchingProfile, newItem: MatchingProfile): Boolean {
        return oldItem == newItem
    }
}
