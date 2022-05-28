package main.cypher;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public int pickAnOption() {

        System.out.println("What would you like to do today?");
        System.out.println("1. Encrypt.");
        System.out.println("2. Decrypt.");

        while (true) {
            if(scanner.nextLine().equals("1")) {
                System.out.println("Excellent choice.");
                return 1;
            } else if(scanner.nextLine().equals("2")){
                System.out.println("Fantastic idea!");
                return 2;
            } else {
                System.out.println("Please pick a valid option!");
            }
        }

    }
}
