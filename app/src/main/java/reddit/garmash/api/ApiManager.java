package reddit.garmash.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import reddit.garmash.utils.Const;
import reddit.garmash.viewcontrollers.main.models.RedditPost;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager {


    private static ApiManager singleton;
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private RedditApiInterface service;

    private ApiManager(Context appContext, String URL) {
        OkHttpClient.Builder builderForHttp = new OkHttpClient.Builder();

        if (Const.LOG_ENABLED) {
            builderForHttp.readTimeout(10, TimeUnit.MINUTES);
            builderForHttp.writeTimeout(10, TimeUnit.MINUTES);
            builderForHttp.connectTimeout(10, TimeUnit.MINUTES);
            builderForHttp.addInterceptor(new LoggingInterceptor(appContext));

        } else {
            builderForHttp.readTimeout(120, TimeUnit.SECONDS);
            builderForHttp.writeTimeout(120, TimeUnit.SECONDS);
            builderForHttp.connectTimeout(120, TimeUnit.SECONDS);
        }

        okHttpClient = builderForHttp.build();

//        Gson gson =
//                new GsonBuilder()
//                        .registerTypeAdapterFactory(new PostsDeserializer()) //TODO check this shit
//                        .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())

//                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(RedditApiInterface.class);
    }

    public RedditApiInterface getService() {
        return service;
    }

    public synchronized static ApiManager getInstance(Context context) {
        if (null == singleton) {
            singleton = new ApiManager(context, Const.URL);
        }
        return singleton;
    }

}
