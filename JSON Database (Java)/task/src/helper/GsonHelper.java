package helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonHelper<T> {

    private Gson gson;

    public GsonHelper() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        this.gson = gsonBuilder
                .create();
    }

    public String serializeJson(T object) {
        return gson.toJson(object);
    }
    public T deserializeJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
