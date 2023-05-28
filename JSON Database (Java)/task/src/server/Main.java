package server;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Database database = new Database();
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            String[] tokens = input.split(" ", 3);
            String command = tokens[0];
            int index = Integer.parseInt(tokens[1]) - 1;
            switch (command) {
                case "set":
                    String text = tokens[2];
                    System.out.println(database.set(index, text) ? "OK" : "ERROR");
                    break;
                case "get":
                    System.out.println(database.get(index));
                    break;
                case "delete":
                    System.out.println(database.delete(index) ? "OK" : "ERROR");
                    break;
            }
        }
    }
}
