package server;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static boolean isIndexInRange(int index) {
        return index >= 0 && index <= 99;
    }

    public static void main(String[] args) {
        final String[] db = new String[100];
        Arrays.fill(db, "");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            input = scanner.nextLine();
            if (input.startsWith("set")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (isIndexInRange(index)) {
                    String toBeSavedText = input.replaceFirst("set ", "").replaceFirst(index + " ", "");
                    db[index] = toBeSavedText;
                    System.out.println("OK");
                } else {
                    System.out.println("ERROR");
                }
            } else if (input.startsWith("get")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (!isIndexInRange(index) || db[index].isBlank()) {
                    System.out.println("ERROR");
                } else {
                    System.out.println(db[index]);
                }
            } else if (input.startsWith("delete")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (isIndexInRange(index)) {
                    db[index] = "";
                    System.out.println("OK");
                } else {
                    System.out.println("ERROR");
                }
            }
        } while (!"exit".equals(input));
    }
}
