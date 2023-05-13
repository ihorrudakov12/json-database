package server;

import com.google.gson.*;
import com.google.gson.JsonElement;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DataBase {
    String path = System.getProperty("user.dir") + "/src/server/data/db.json";
    Gson gson = new Gson();
    private static final Path DB_PATH = Path.of(System.getProperty("user.dir") + "/src/server/data/db.json");
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    JsonObject database;

    public DataBase() throws IOException {
        if (Files.exists(DB_PATH)) {
            String content = new String(Files.readAllBytes(DB_PATH));
            database = new Gson().fromJson(content, JsonObject.class);
            if (database == null) {
                database = new JsonObject();
                writeFile();
            }
        } else {
            Files.createFile(DB_PATH);
            database = new JsonObject();
            writeFile();
        }
    }

    public JsonElement setValue(JsonElement key, JsonElement value) {
        JsonElement element = null;
        try {
            writeLock.lock();
            if (key.isJsonPrimitive()) {
                database.add(key.getAsString(), value);
            } else if (key.isJsonArray()) {
                JsonArray keys = key.getAsJsonArray();
                String toAdd = keys.remove(keys.size() - 1).getAsString();
                findElement(keys, true).getAsJsonObject().add(toAdd, value);
            } else {
                throw new RuntimeException();
            }
            writeFile();
        } finally {
            writeLock.unlock();
        }
        return element;
    }

    public JsonElement getValue(JsonElement key) {
        try {
            readLock.lock();
            if (key.isJsonPrimitive() && database.has(key.getAsString())) {
                return database.get(key.getAsString());
            } else if (key.isJsonArray()) {
                return findElement(key.getAsJsonArray(), false);
            }
            throw new RuntimeException();
        } finally {
            readLock.unlock();
        }
    }

    public JsonElement removeValue(JsonElement key) {
        JsonElement element = null;
        try {
            writeLock.lock();
            if (key.isJsonPrimitive() && database.has(key.getAsString())) {
                element = database.remove(key.getAsString());
            } else if (key.isJsonArray()) {
                JsonArray keys = key.getAsJsonArray();
                String toRemove = keys.remove(keys.size() - 1).getAsString();
                element = findElement(keys, false).getAsJsonObject().remove(toRemove);
                writeFile();
            } else {
                throw new RuntimeException("");
            }
        } finally {
            writeLock.unlock();
        }
        return element;
    }

    private JsonElement findElement(JsonArray keys, boolean createIfAbsent) {
        JsonElement tmp = database;
        if (createIfAbsent) {
            for (JsonElement key: keys) {
                if (!tmp.getAsJsonObject().has(key.getAsString())) {
                    tmp.getAsJsonObject().add(key.getAsString(), new JsonObject());
                }
                tmp = tmp.getAsJsonObject().get(key.getAsString());
            }
        } else {
            for (JsonElement key: keys) {
                if (!key.isJsonPrimitive() || !tmp.getAsJsonObject().has(key.getAsString())) {
                    throw new RuntimeException();
                }
                tmp = tmp.getAsJsonObject().get(key.getAsString());
            }
        }
        return tmp;
    }

    private void writeFile() {
        try (Writer writer = Files.newBufferedWriter(Paths.get(path), StandardCharsets.UTF_8)) {
            String dbJson = gson.toJson(database);
            writer.write(dbJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
