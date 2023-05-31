package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 34522);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {
            System.out.println("Client started!");

            String toBeSent = "Give me a record # 12";
            output.writeUTF(toBeSent);
            System.out.println("Sent: " + toBeSent);

            String received = input.readUTF();
            System.out.println("Received: " + received);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
