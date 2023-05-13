package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;

public class SetCommand implements Command {
    private DataBase db;
    private JsonElement key;
    private JsonElement value;
    public SetCommand(DataBase db, JsonElement key, JsonElement value) {
        this.db = db;
        this.key = key;
        this.value = value;
    }
    @Override
    public JsonElement execute() {
        return this.db.setValue(this.key, this.value);
    }
}
