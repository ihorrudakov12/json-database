package client;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Args {
    private String type;
    private JsonElement key;
    private JsonElement value;

    public Args(String type, JsonElement key, JsonElement value) {
        this.type = type;
        this.key = key;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public JsonElement getKey() {
        return key;
    }

    public JsonElement getValue() {
        return value;
    }

    @Override
    public String toString() {
        return type + " " + key + " " + value;
    }
}
