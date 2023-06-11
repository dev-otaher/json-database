package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Database {
    private final JsonElement db;

    public Database() {
        db = new JsonObject();
    }

    public String get(String key) {
        JsonElement keyElement = db.getAsJsonObject().get(key);
        return (keyElement == null) ? null : keyElement.getAsString();
    }

    public void set(String key, String value) {
        db.getAsJsonObject().addProperty(key, value);
    }

    public boolean delete(String key) {
        return db.getAsJsonObject().remove(key) != null;
    }
}
