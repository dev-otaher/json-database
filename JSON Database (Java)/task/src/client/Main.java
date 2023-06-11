package client;

import com.beust.jcommander.JCommander;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        RequestArgs requestArgs = new RequestArgs();
        JCommander.newBuilder().addObject(requestArgs).build().parse(args);
        try (Socket socket = new Socket("127.0.0.1", 34522);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

            System.out.println("Client started!");

            String request = new Gson().toJson(requestArgs);
//            String request = "{ \"type\": \"set\", \"key\": \"Secret key\", \"value\": \"Secret value\" }";
//            String request = "{ \"type\": \"get\", \"key\": \"Secret key\" }";
//            String request = "{ \"type\": \"delete\", \"key\": \"Secret key\" }";
//            String request = "{ \"type\": \"exit\" }";
            output.writeUTF(request);
            System.out.println("Sent: " + request);

            String response = input.readUTF();
            System.out.println("Received: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
