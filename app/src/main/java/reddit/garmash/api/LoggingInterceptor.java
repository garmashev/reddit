package reddit.garmash.api;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;


class LoggingInterceptor implements Interceptor {
    private Context context;
    private String TAG = "LoggingInterceptor";

    public LoggingInterceptor(Context context) {
        super();
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        Log.e(TAG,String.format("Sending request %s", request.url()));
        String body = "";
        if (request.method().compareToIgnoreCase("post") == 0) {
            body = bodyToString(request);
            Log.e(TAG,String.format("Body:%s", body));

        }
        Response response = null;
        response = chain.proceed(request);


        long t2 = System.nanoTime();

        String bodyString = null;
        double timeOfExecution = -1;
        try {
            bodyString = response.body().string();
            timeOfExecution = ((t2 - t1) / 1000000000.0);
            Log.e(TAG,String.format("Received response for %s in %.1fs%n%s",
                    response.request().url(), timeOfExecution, bodyString));

        } catch (Exception e) {
            e.printStackTrace();
            bodyString = "";
        }


        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
    }

    public static String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}