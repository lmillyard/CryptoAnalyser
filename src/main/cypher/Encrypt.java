package main.cypher;

import main.fileOperations.ReadFile;
import main.fileOperations.WriteFile;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Encrypt {


    static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    static String fileName = null;
    static String contents = null;
    static int offset = 0;

    public void start() {

//Makes sure file exists else loops back to picking a file.

        while (true) {
            System.out.println("Pick a file to encrypt");
            fileName = scanner.nextLine();

            File file = new File(fileName);

            if (file.exists()) {
                ReadFile reader = new ReadFile();
                contents = reader.readFile(fileName);
                break;
            } else {
                System.out.println("That file does not exist.");
            }
        }
        System.out.println("Pick a shift number for the encryption.");

// Making sure user can only enter an integer for the encryption offset
        while (true) {

            if(! scanner.hasNextInt()){
                scanner.next();
                System.out.println("Please pick an integer.");
//TODO MAKE SURE ONLY POSITIVE NUMBERS CAN BE ENTERED
            } else {
                offset = scanner.nextInt();
                break;
            }
        }

        CaesarCypher cypher = new CaesarCypher();
        String encodedMessage = cypher.encrypt(contents, offset);

        System.out.println("You're enigmatic results are.. Dun dun duuunnn... \n");
        System.out.println(encodedMessage);

        System.out.println("Shall we save your results, yes or no?");
// checks for valid input and saves a text file.
        while(true) {
            String choice = scanner.nextLine();
            if(choice.equalsIgnoreCase("yes")){

                System.out.println("Great what shall we name our file?");

                String newFile = scanner.nextLine();
                WriteFile writer = new WriteFile();

                writer.writeToFile(newFile, encodedMessage);

                System.out.println("File: \"" + newFile + "\" saved!");
                break;

            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("Excellent, moving on!");
                break;
            } else {
                System.out.println("please enter yes or no!");
            }
        }


        scanner.close();
    }

    }

