package reddit.garmash.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import reddit.garmash.viewcontrollers.main.models.RedditPost;

public class PostsDeserializer implements TypeAdapterFactory { // TODO FIX this


    @Override
    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                T result = null;
                in.beginObject();

                List<RedditPost> posts = new ArrayList<>();
                JsonElement jsonElement = elementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    if (jsonObject.has("data")) {
                        JsonObject data = jsonObject.get("data").getAsJsonObject();
                        jsonElement = data.get("children");
                    }
                }

                if (jsonElement.isJsonArray()) {
                    JsonArray jsonArray = jsonElement.getAsJsonArray();

                    for (JsonElement jsonObj : jsonArray) {

                        JsonElement postJson = jsonObj.getAsJsonObject().get("data");

                        posts.add(new Gson().fromJson(postJson, RedditPost.class));

                    }
                    result = (T) posts;

                }

                if (result == null){

                    result = new Gson().fromJson(in, (Type) type);
                }




                return result;
            }
        }.nullSafe();
    }


}
