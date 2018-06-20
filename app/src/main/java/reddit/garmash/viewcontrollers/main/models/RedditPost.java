package reddit.garmash.viewcontrollers.main.models;

import com.google.gson.annotations.SerializedName;

/**
 * Title (at its full length)
 * Author and subreddit
 * Post date (format like “x hours ago”)
 * A thumbnail for those who have a picture
 * Current rating and number of comments
 */

public class RedditPost {


    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("thumbnail")
    private String thumbnail = "";

    @SerializedName("subreddit")
    private String subreddit;

    @SerializedName("author")
    private String author;

    @SerializedName("num_comments")
    private long numComments;

    @SerializedName("score")
    private long score;

    @SerializedName("created_utc")
    private long created;


    public String getId() {
        return id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public String getAuthor() {
        return author;
    }

    public long getNumComments() {
        return numComments;
    }

    public long getScore() {
        return score;
    }

    public long getCreated() {

        return created;
    }

    public String getTitle() {
        return title;
    }


}

