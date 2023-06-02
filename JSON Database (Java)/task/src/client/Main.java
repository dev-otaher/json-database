package client;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        Args clientArgs = new Args();
        JCommander.newBuilder().addObject(clientArgs).build().parse(args);
        try (Socket socket = new Socket("127.0.0.1", 34522);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Client started!");

            String request = String.format("%s %d %s", clientArgs.requestType, clientArgs.index, clientArgs.data);
            output.writeUTF(request);
            System.out.println("Sent: " + request);

            String response = input.readUTF();
            System.out.println("Received: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
