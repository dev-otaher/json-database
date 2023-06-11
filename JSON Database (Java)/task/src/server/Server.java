package server;

import com.google.gson.Gson;
import server.request.Request;
import server.request.RequestType;
import server.response.ErrorResponse;
import server.response.OkResponse;
import server.response.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static server.request.RequestType.EXIT;

public class Server {
    private final Database db;
    private final Gson gson;
    private final ServerSocket serverSocket;

    public Server(Database db, Gson gson, ServerSocket serverSocket) {
        this.db = db;
        this.gson = gson;
        this.serverSocket = serverSocket;
    }

    public void start() {
        try (serverSocket) {
            System.out.println("Server started!");
            boolean running = true;
            while (running) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream input = new DataInputStream(socket.getInputStream());
                     DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
                    String requestString = input.readUTF();
                    Request request = gson.fromJson(requestString, Request.class);
                    running = !request.getType().equals(EXIT);
                    Response response = getResponse(request);
                    output.writeUTF(gson.toJson(response));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Response getResponse(Request request) {
        RequestType requestType = request.getType();
        switch (requestType) {
            case SET -> {
                db.set(request.getKey(), request.getValue());
                return new OkResponse(null);
            }
            case GET -> {
                String value = db.get(request.getKey());
                return value == null ? new ErrorResponse("No such key") : new OkResponse(value);
            }
            case DELETE -> {
                return db.delete(request.getKey()) ? new OkResponse(null) : new ErrorResponse("No such key");
            }
            case EXIT -> {
                return new OkResponse(null);
            }
            default -> {
                return null;
            }
        }
    }
}
