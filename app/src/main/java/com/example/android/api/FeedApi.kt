package com.example.android.api

import com.example.android.model.FeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeedApi {

    @GET("/photos")
    suspend fun getFeeds() : Response<List<FeedResponse>>

}