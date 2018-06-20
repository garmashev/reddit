package reddit.garmash.viewcontrollers.main.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.curioustechizen.ago.RelativeTimeTextView

import reddit.garmash.viewcontrollers.main.R
import reddit.garmash.viewcontrollers.main.models.RedditPost
import java.lang.StringBuilder

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle: TextView = itemView.findViewById<View>(R.id.tv_title) as TextView
    private val tvSubreddit: TextView = itemView.findViewById<View>(R.id.tv_subreddit) as TextView
    private val tvAuthor: TextView = itemView.findViewById<View>(R.id.tv_author) as TextView
    private val tvCommentsCount: TextView = itemView.findViewById<View>(R.id.tv_comments_count) as TextView
    private val tvDate: RelativeTimeTextView = itemView.findViewById<View>(R.id.tv_date) as RelativeTimeTextView
    private val tvRatingCount: TextView = itemView.findViewById<View>(R.id.tv_rating_count) as TextView
    private val ivPostImage: ImageView = itemView.findViewById<View>(R.id.iv_preview) as ImageView




    fun bind(post : RedditPost){
        tvTitle.text = post.title
        tvSubreddit.text = post.subreddit
        tvAuthor.text = post.author
        tvCommentsCount.text = StringBuilder().append("Comments - ").append(post.numComments)
        tvRatingCount.text = StringBuilder().append("Rating - ").append(post.score)
        tvDate.setReferenceTime(post.created)
        if(!post.thumbnail.isNullOrEmpty())
        Glide.with(itemView.context).load(post.thumbnail).apply(RequestOptions().placeholder(R.mipmap.ic_launcher)).into(ivPostImage)
    }



    fun clear(){
        ivPostImage.invalidate()
        Log.e("####", "holder clear")
    }


}
