package reddit.garmash.api;


import reddit.garmash.viewcontrollers.main.models.RedditResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RedditApiInterface {

    @GET("top/.json")
    Call<RedditResponse> getTop(@Query("count") int startFrom, @Query("limit") int limit);
}
