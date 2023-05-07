package com.example.android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.repository.FeedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val feedRepository: FeedRepository) : ViewModel() {

    val liveData get() = feedRepository.liveData

    fun getFeeds() = viewModelScope.launch {
        feedRepository.getFeeds()
    }

}