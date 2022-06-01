package main.interactives;
//Initial Menu options to find out what the user wants to do: encrypt/decrypt/exit

import main.cypher.CaesarCypher;
import main.fileOperations.ReadFile;
import main.fileOperations.WriteFile;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {



    public int pickAnOption() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

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

    public String pickAFile(){
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String fileName;


        while (true) {
            System.out.println("Pick a file.");
            fileName = scanner.nextLine();
            File file = new File(fileName);

            if (! file.exists()) {
                System.out.println("That file does not exist.");
                continue;
            } ReadFile reader = new ReadFile(fileName);

            return reader.readFile();
        }
    }

    public int pickAnOffset() {
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
              System.out.println("Pick a shift number.");

        while (true) {

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please pick an integer.");
                continue;
//TODO MAKE SURE ONLY POSITIVE NUMBERS CAN BE ENTERED
            }

            return scanner.nextInt();

        }

    }

    public void saveFile(String message) {
        System.out.println("Shall we save your results, yes or no?");
        Scanner scanner = new Scanner(new InputStreamReader(System.in));

// checks for valid input and saves a text file.
        while(true) {
            String saveChoice = scanner.nextLine();
            if(saveChoice.equalsIgnoreCase("yes")){

                System.out.println("Great what shall we name our file?");

                String newFile = scanner.nextLine();
                WriteFile writer = new WriteFile();

                writer.writeToFile(newFile, message);

                System.out.println("File: \"" + newFile + "\" saved!");
                break;

            } else if (saveChoice.equalsIgnoreCase("no")) {
                System.out.println("Excellent, moving on!\n");
                break;
            } else {
                System.out.println("please enter yes or no!");
            }
        }
    }


    public void encrypt(String contents, int offset){

        Scanner scanner = new Scanner(new InputStreamReader(System.in));


            CaesarCypher cypher = new CaesarCypher();
            String encodedMessage = cypher.encrypt(contents, offset);

            System.out.println("You're enigmatic results are.. Dun dun duuunnn... \n");
            System.out.println(encodedMessage);

            System.out.println("Shall we save your results, yes or no?");
// checks for valid input and saves a text file.
            while (true) {
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("yes")) {

                    System.out.println("Great what shall we name our file?");

                    String newFile = scanner.nextLine();
                    WriteFile writer = new WriteFile();

                    writer.writeToFile(newFile, encodedMessage);

                    System.out.println("File: \"" + newFile + "\" saved!\n");
                    break;

                } else if (choice.equalsIgnoreCase("no")) {
                    System.out.println("Excellent, moving on!\n");
                    break;
                } else {
                    System.out.println("please enter yes or no!");
                }
            }

        }

        public void decrypt(String contents) {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));

            System.out.println("Do you know the key, yes/no?");
            while (true) {
                String choice = scanner.nextLine();

                if (choice.equalsIgnoreCase("yes")) {
                    System.out.println("Great, enter it now!");
                    int offset = pickAnOffset();

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
                            break;
                        } else if (pickChoice == 2) {
                            System.out.println("Statistically! Fantastic idea!\n");
                            RunStatisticalCrack runStatisticalCrack = new RunStatisticalCrack(contents);
                            runStatisticalCrack.run();
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
