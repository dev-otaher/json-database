package server;

import java.util.Arrays;

public class Database {
    private final String[] db;

    public Database() {
        db = new String[100];
        Arrays.fill(db, "");
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index <= 99;
    }

    public String get(int index) {
        return !isValidIndex(index) || db[index].isBlank() ? "ERROR" : db[index];
    }

    public boolean set(int index, String text) {
        if (isValidIndex(index)) {
            db[index] = text;
            return true;
        }
        return false;
    }

    public boolean delete(int index) {
        if (isValidIndex(index)) {
            db[index] = "";
            return true;
        }
        return false;
    }

}
