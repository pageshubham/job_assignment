package com.example.android.di

import com.example.android.util.Constants.BASE_URL
import com.example.android.api.FeedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitBuilder() : Retrofit.Builder {
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideFeedApi(retrofitBuilder: Retrofit.Builder) : FeedApi {
        return retrofitBuilder.build().create(FeedApi::class.java)
    }

}