package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(34522)) {
            System.out.println("Server started!");
            Socket socket = server.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String received = input.readUTF();
            System.out.println("Received: " + received);

            String toBeSent = "A record # 12 was sent!";
            output.writeUTF(toBeSent);
            System.out.println("Sent: " + toBeSent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}