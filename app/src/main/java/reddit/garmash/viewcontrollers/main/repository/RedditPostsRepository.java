package reddit.garmash.viewcontrollers.main.repository;


public interface RedditPostsRepository {

    void getRedditPosts(int start, int finish, RedditPostsRepositoryImpl.OnLoadComplite onLoadComplite);

}
