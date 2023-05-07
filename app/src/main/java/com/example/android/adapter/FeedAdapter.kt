package com.example.android.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.databinding.FeedItemBinding
import com.example.android.model.FeedResponse
import com.squareup.picasso.Picasso

class FeedAdapter(private val onFeedClicked: (FeedResponse) -> Unit) :
    ListAdapter<FeedResponse, FeedAdapter.NoteViewHolder>(ComparatorDiffUtil()) {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = FeedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        note?.let {
            holder.bind(it)
        }
    }

    inner class NoteViewHolder(private val binding: FeedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(feed: FeedResponse) {
            Picasso.with(context).load(feed.thumbnailUrl).into(binding.feedImage)
            binding.feedTitle.text = feed.title
            binding.root.setOnClickListener {
                onFeedClicked(feed)
            }
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<FeedResponse>() {
        override fun areItemsTheSame(oldItem: FeedResponse, newItem: FeedResponse): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FeedResponse, newItem: FeedResponse): Boolean {
            return oldItem == newItem
        }
    }
}