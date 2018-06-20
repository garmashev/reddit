package reddit.garmash.viewcontrollers.main.repository

import android.content.Context
import android.support.annotation.NonNull

import java.util.ArrayList

import reddit.garmash.api.ApiManager
import reddit.garmash.viewcontrollers.main.models.RedditPost
import reddit.garmash.viewcontrollers.main.models.RedditResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RedditPostsRepositoryImpl(private val context: Context)// TODO inject
    : RedditPostsRepository {

    override fun getRedditPosts(start: Int, finish: Int, @NonNull onLoadComplite: OnLoadComplite) {

        val call = ApiManager.getInstance(context).service.getTop(start, finish)
        call.enqueue(object : Callback<RedditResponse> {
            override fun onResponse(call: Call<RedditResponse>, response: Response<RedditResponse>) {
                if (response.isSuccessful && response.code() == 200) {
                    onLoadComplite.onLoadComlite(response.body()?.redditPosts)
                } else {
                    onLoadComplite.onFailure(response.message()) //TODO load from DB or retrofit cache
                }

            }

            override fun onFailure(call: Call<RedditResponse>, t: Throwable) {
                onLoadComplite.onFailure(t.localizedMessage) //TODO load from DB or retrofit cache
            }
        })
    }

    interface OnLoadComplite {

        fun onLoadComlite(tList: List<RedditPost>?)

        fun onFailure(error: String)


    }
}
