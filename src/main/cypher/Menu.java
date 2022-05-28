package main.cypher;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public int pickAnOption() {

        System.out.println("What would you like to do today?");
        System.out.println("1. Encrypt.");
        System.out.println("2. Decrypt.");
        System.out.println("0. To Exit");

        while (true) {
            if (! scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please pick an integer.");
                continue;
            }
            int choice = scanner.nextInt();
            if (choice == 0) {
                return 0;
            }
            if (choice == 1) {
                System.out.println("Excellent choice.");
                return 1;
            } else if (choice == 2) {
                System.out.println("Fantastic idea!");
                return 2;
            } else {
                System.out.println("Please pick a valid option!");
            }
        }

    }
}
