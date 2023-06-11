package server;

import com.google.gson.Gson;
import server.request.Request;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        Database db = new Database();
        Gson gson = new Gson()
                .newBuilder()
                .registerTypeAdapter(Request.class, new RequestJsonDeserializer())
                .create();
        ServerSocket serverSocket = new ServerSocket(34522);
        new Server(db, gson, serverSocket).start();
    }
}