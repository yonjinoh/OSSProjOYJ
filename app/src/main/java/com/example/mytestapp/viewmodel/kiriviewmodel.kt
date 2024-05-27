package com.example.mytestapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mytestapp.model.request.MatchingProfile

class KiriViewModel(application: Application?) : AndroidViewModel(application!!) {
    private val _selectedProfile = MutableLiveData<MatchingProfile>()
    val selectedProfile: LiveData<MatchingProfile>
        get() = _selectedProfile

    fun onProfileClicked(profile: MatchingProfile) {
        _selectedProfile.value = profile
    }
}
