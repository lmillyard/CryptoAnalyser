package main.interactives;
//Output to console: uses the CaesarCypher/Break class to decrypt a given file and outputs options to the user asking whether they
//know the offset key or need to crack it - returns the cracked code.

import main.cypher.CaesarCypher;
import main.fileOperations.ReadFile;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Decrypt {

    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void start() {

        String contents;
        while (true) {
            System.out.println("Pick a file to decrypt");
            String fileName = scanner.nextLine();
            File file = new File(fileName);

            if (file.exists()) {

                ReadFile reader = new ReadFile(fileName);
                contents = reader.readFile();
                break;

            } else {

                System.out.println("That file does not exist.");
            }
        }

        System.out.println("Do you know the key, yes/no?");
        while (true) {
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("yes")) {
                System.out.println("Great, enter it now!");
                int offset;

                while (true) {

                    if (!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("Please pick an integer.");

                    } else {
                        offset = scanner.nextInt();
                        break;
                    }
                }

                CaesarCypher cypher = new CaesarCypher();
                String decryptedMessage = cypher.decrypt(contents, offset);

                System.out.println("Here is your decrypted message! \n--------------------------\n" + decryptedMessage);
                break;

            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("\nHmmm, Okay lets try cracking it then!");
                System.out.println("Please pick how you would like to decrypt your file.");
                System.out.println("1. Brute Force.");
                System.out.println("2. Statistically");

                while (true) {
                    if (!scanner.hasNextInt()) {
                        scanner.next();
                        System.out.println("Please pick an integer.");
                        continue;
                    }
                    int pickChoice = scanner.nextInt();

                    if (pickChoice == 1) {
                        System.out.println("Brute force! Excellent choice.\n");
                        //run brute force
                        RunBruteCrack runBruteCrack = new RunBruteCrack(contents);
                        runBruteCrack.run();
                    } else if (pickChoice == 2) {
                        System.out.println("Statistically! Fantastic idea!\n");
                        RunStatisticalDecrypt runStatisticalDecrypt = new RunStatisticalDecrypt(contents);
                        runStatisticalDecrypt.run();
                        break;
                    } else {
                        System.out.println("please pick a valid option!");
                    }
                }
                    } else {
                        System.out.println("Please pick a valid option!");
                        continue;
                    }break;


        }

    }
}
