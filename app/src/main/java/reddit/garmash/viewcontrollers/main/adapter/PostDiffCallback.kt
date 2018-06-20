package reddit.garmash.viewcontrollers.main.adapter

import android.support.v7.util.DiffUtil

import reddit.garmash.viewcontrollers.main.models.RedditPost

class PostDiffCallback : DiffUtil.ItemCallback<RedditPost>() {

    override fun areItemsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RedditPost, newItem: RedditPost): Boolean {
        return oldItem == newItem
    }
}
