package reddit.garmash.viewcontrollers.main.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RedditData {


    @SerializedName("children")
    private ArrayList<RedditChildren> redditChildren;

    public ArrayList<RedditChildren> getRedditChildren() {
        return redditChildren;
    }

    public void setRedditChildren(ArrayList<RedditChildren> redditChildren) {
        this.redditChildren = redditChildren;
    }
}
