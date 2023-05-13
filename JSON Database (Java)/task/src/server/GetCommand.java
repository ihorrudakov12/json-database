package server;

import com.google.gson.JsonElement;

public class GetCommand implements Command {

    private DataBase db;
    private JsonElement key;
    public GetCommand(DataBase db, JsonElement key) {
        this.db = db;
        this.key = key;
    }
    @Override
    public JsonElement execute() {
        return this.db.getValue(this.key);
    }
}
