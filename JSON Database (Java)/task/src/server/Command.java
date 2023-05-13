package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface Command {
    JsonElement execute();
}
