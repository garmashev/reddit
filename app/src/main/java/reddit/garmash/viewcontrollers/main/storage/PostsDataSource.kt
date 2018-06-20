package reddit.garmash.viewcontrollers.main.storage

import android.arch.paging.PositionalDataSource
import android.content.Context
import android.util.Log

import reddit.garmash.viewcontrollers.main.models.RedditPost
import reddit.garmash.viewcontrollers.main.repository.RedditPostsRepository
import reddit.garmash.viewcontrollers.main.repository.RedditPostsRepositoryImpl

class PostsDataSource constructor(context: Context) : PositionalDataSource<RedditPost>() {

    private var redditPostsRepository: RedditPostsRepository = RedditPostsRepositoryImpl(context)

    override fun loadInitial(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<RedditPost>) {
        redditPostsRepository.getRedditPosts(params.requestedStartPosition, params.requestedLoadSize, object : RedditPostsRepositoryImpl.OnLoadComplite {
            override fun onLoadComlite(redditPosts: List<RedditPost>?) {
                callback.onResult(redditPosts!!, params.requestedStartPosition)
            }

            override fun onFailure(error: String) {
                // TODO handle error
                Log.e("###", error)
            }
        })
    }

    override fun loadRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<RedditPost>) {
        redditPostsRepository.getRedditPosts(params.startPosition, params.loadSize, object : RedditPostsRepositoryImpl.OnLoadComplite {
            override fun onLoadComlite(redditPosts: List<RedditPost>?) {
                callback.onResult(redditPosts!!)
            }

            override fun onFailure(error: String) {
                Log.e("###", error)
                // TODO handle error
            }
        })
    }
}
