package com.example.android.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.api.FeedApi
import com.example.android.model.FeedResponse
import com.example.android.util.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class FeedRepository @Inject constructor(private val feedApi: FeedApi) {

    private val _liveData = MutableLiveData<NetworkResult<List<FeedResponse>>>()
    val liveData : LiveData<NetworkResult<List<FeedResponse>>> = _liveData

    suspend fun getFeeds() {
        _liveData.value = NetworkResult.Loading()
        val response = feedApi.getFeeds()

        when {
            response.isSuccessful && response.body() != null -> {
                _liveData.value = NetworkResult.Success(response.body())
            }
            response.errorBody() != null -> {
                val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
                _liveData.value = NetworkResult.Error(errorObj.getString("message"))
            }
            else -> {
                _liveData.value = NetworkResult.Error("Something went wrong")
            }
        }
    }
}