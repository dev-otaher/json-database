package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(34522)) {
            Database db = new Database(1000);
            System.out.println("Server started!");
            boolean running = true;
            while (running) {
                try (Socket socket = server.accept();
                     DataInputStream input = new DataInputStream(socket.getInputStream());
                     DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
                    String fromClient = input.readUTF();
                    String response = "";

                    if (fromClient.contains("exit")) {
                        response = "OK";
                        running = false;
                    } else {
                        String[] tokens = fromClient.split(" ", 3);
                        String command = tokens[0];
                        int index = Integer.parseInt(tokens[1]) - 1;
                        switch (command) {
                            case "set":
                                String text = tokens[2];
                                response = db.set(index, text) ? "OK" : "ERROR";
                                break;
                            case "get":
                                response = db.get(index);
                                break;
                            case "delete":
                                response = db.delete(index) ? "OK" : "ERROR";
                                break;
                        }
                    }
                    output.writeUTF(response);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}