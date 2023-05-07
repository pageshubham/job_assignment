package com.example.android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.databinding.ActivityFeedBinding
import com.example.android.model.FeedResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class FeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFeedBinding
    private var feed: FeedResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setInitialData()
    }

    private fun setInitialData() {
        val jsonFeed = intent.getStringExtra("feed")
        if (jsonFeed != null) {
            feed = Gson().fromJson(jsonFeed, FeedResponse::class.java)
            feed?.let {
                Picasso.with(this).load(it.url).into(binding.feedImage)
                binding.feedId.text = it.id.toString()
                binding.feedTitle.text = it.title
            }
        }
    }
}