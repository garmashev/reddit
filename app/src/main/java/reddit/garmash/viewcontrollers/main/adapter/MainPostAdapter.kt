package reddit.garmash.viewcontrollers.main.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.AsyncDifferConfig
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import reddit.garmash.viewcontrollers.main.R
import reddit.garmash.viewcontrollers.main.models.RedditPost

class MainPostAdapter(diffCallback: DiffUtil.ItemCallback<RedditPost>) : PagedListAdapter<RedditPost, PostViewHolder>(diffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reddit_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
//        if (post != null){
            holder.bind(post!!)
//        } else {
//            holder.clear()
//        }
    }
}
