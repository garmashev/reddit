package reddit.garmash.viewcontrollers.main.models;

import com.google.gson.annotations.SerializedName;

public class RedditChildren {

    @SerializedName("data")
    private RedditPost redditPost;

    public RedditPost getRedditPost() {
        return redditPost;
    }

    public void setRedditPost(RedditPost redditPost) {
        this.redditPost = redditPost;
    }
}
