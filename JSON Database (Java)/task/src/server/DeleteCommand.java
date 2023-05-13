package server;

import com.google.gson.JsonElement;

public class DeleteCommand implements Command {

    private DataBase db;
    private JsonElement key;
    public DeleteCommand(DataBase db, JsonElement key) {
        this.db = db;
        this.key = key;
    }
    @Override
    public JsonElement execute() {
        return this.db.removeValue(this.key);
    }
}
