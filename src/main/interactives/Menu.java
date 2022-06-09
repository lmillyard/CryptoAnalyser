package main.interactives;
//Initial Menu options to find out what the user wants to do: encrypt/decrypt/exit

import main.cypher.CaesarCypher;
import main.fileOperations.ReadFile;
import main.fileOperations.WriteFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Menu {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public int pickAnOption() {

    public int pickAnOption() {

        System.out.println("What would you like to do today?");
        System.out.println("1. Encrypt.");
        System.out.println("2. Decrypt.");
        System.out.println("0. To Exit");

        while (true) {
            try {
                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        System.out.println("Excellent choice.");
                        return 1;
                    case "2":
                        System.out.println("Fantastic idea!");
                        return 2;
                    case "0":
                        return 0;
                    default:
                        System.out.println("Please pick a valid number.");

                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected String pickAFile() {
        String fileName;
        while (true) {
            try {
                System.out.println("Pick a file.");
                fileName = reader.readLine();
                File file = new File(fileName);

                if (!file.exists()) {
                    System.out.println("That file does not exist.");
                    continue;
                }
                ReadFile reader = new ReadFile(fileName);
                return reader.readFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO MAKE SURE ONLY POSITIVE NUMBERS CAN BE ENTERED
    protected int pickAnOffset() {
        int offset;
        System.out.println("Pick a shift number.");

        while (true) {

            if (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please pick an integer.");
            } else {
                offset = scanner.nextInt();
                return offset;
            }
        }
    }


    protected String pickALanguage() {

        System.out.println("Please pick which language your file is written in.");
        System.out.println("1. English.");
        System.out.println("2. Other.");

        while (true) {
            try {
                String choice = reader.readLine();
                switch (choice) {
                    case "1" -> {
                        System.out.println("English, Great!\n");
                        return "English";
                    }
                    case "2" -> {
                        System.out.println("Other, alright.");
                        return "Other";
                    }
                    default -> System.out.println("Please pick an integer.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    protected boolean pickDefaultRules(String language) {

        if (language.equalsIgnoreCase("english")) {
            System.out.println("Please pick an option");
            System.out.println("1. Use default rules.");
            System.out.println("2. Use a sample from the same author.");

            while (true) {
                try {
                    String choice = reader.readLine();

                    switch (choice) {
                        case "1" -> {
                            System.out.println("Default, Excellent!");
                            return true;
                        }
                        case "2" -> {
                            System.out.println("Use your own, smashing.");
                            return false;
                        }
                        default -> System.out.println("Please pick option 1 or 2!");
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println("Please upload an un-coded text file, preferably from the same author for us to study.");
            return false;
        }
    }


    protected void saveFile(String message) {

        System.out.println("Shall we save your results, yes or no?");

        while(true) {
            try {
                String saveChoice = reader.readLine();

                switch (saveChoice.toUpperCase()) {
                    case "YES" -> {
                        System.out.println("Great what shall we name our file?");
                        String newFile = reader.readLine();
                        WriteFile writer = new WriteFile();
                        writer.writeToFile(newFile, message);
                        System.out.println("File: \"" + newFile + "\" saved!\n");
                    }
                    case "NO" -> System.out.println("Excellent, moving on!\n");
                    default -> {
                        System.out.println("please enter yes or no!");
                        continue;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }break;
        }
    }


    protected void encrypt(String contents, int offset) {
        CaesarCypher cypher = new CaesarCypher();
        String encodedMessage = cypher.encrypt(contents, offset);

        System.out.println("You're enigmatic results are.. Dun dun duuunnn... \n");
        System.out.println(encodedMessage);

        saveFile(encodedMessage);
    }

    protected void decrypt(String contents, String language) {

        while(true) {
            try {
                System.out.println("Do you know the key, yes/no?");
                String choice = reader.readLine();

                switch (choice.toUpperCase()) {
                    case "YES" -> {
                        System.out.println("Great, enter it now!");
                        int offset = pickAnOffset();
                        CaesarCypher cypher = new CaesarCypher();
                        String decryptedMessage = cypher.decrypt(contents, offset);
                        System.out.println("Here is your decrypted message! \n--------------------------\n" + decryptedMessage);
                        saveFile(decryptedMessage);
                    }
                    case "NO" -> {
                        if(language.equalsIgnoreCase("english")) {
                            System.out.println("\nHmmm, Okay lets try cracking it then!");
                            System.out.println("Please pick how you would like to decrypt your file.");
                            System.out.println("1. Brute Force.");
                            System.out.println("2. Statistically");

                            while(true) {
                                String option = reader.readLine();
                                switch (option) {
                                    case "1" -> {
                                        System.out.println("Brute force! Excellent choice.\n");
                                        boolean defaultRules = pickDefaultRules(language);
                                        RunBruteCrack runBruteCrack = new RunBruteCrack(contents, defaultRules);
                                        runBruteCrack.run(language);
                                    }
                                    case "2" -> {
                                        System.out.println("Statistically! Fantastic idea!\n");
                                        RunStatisticalCrack runStatisticalCrack = new RunStatisticalCrack(contents);
                                        runStatisticalCrack.run();
                                    }
                                    default -> {
                                        System.out.println("\t\tplease pick a valid option!\n (1 for brute force or 2 for statistical crack.)\n");
                                        continue;
                                    }

                                }break;
                            }
                        } else {
                            System.out.println("Okay, lets try using brute force to crack this!");
                            System.out.println("Please upload an un-coded text file, preferably from the same author for us to study.");
                            RunBruteCrack runBruteCrack = new RunBruteCrack(contents, false);
                            runBruteCrack.run(language);
                        }

                    }
                    default -> System.out.println("please pick a valid option!\n");

                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }break;
        }
    }

//    protected void myBestGuess(){
//
//    }
}