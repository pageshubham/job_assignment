package com.example.android

import com.example.android.api.FeedApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FeedApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var feedApi: FeedApi

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        feedApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("https://jsonplaceholder.typicode.com/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(FeedApi::class.java)
    }

    @Test
    suspend fun testGetFeed() {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = feedApi.getFeeds()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}