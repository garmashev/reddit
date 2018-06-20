package reddit.garmash.viewcontrollers.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RedditResponse {


    @SerializedName("data")
    private RedditData redditData;




    public List<RedditPost> getRedditPosts(){

        ArrayList<RedditPost> redditPosts = new ArrayList<>();

        for (RedditChildren redditChildren:redditData.getRedditChildren()) {

            redditPosts.add(redditChildren.getRedditPost());

        }
        return redditPosts;
    }

}
