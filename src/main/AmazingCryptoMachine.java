package main;

import main.cypher.CaesarCypher;
import main.fileOperations.ReadFile;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

// Interactive menu for encoding a given file (from console) then encoding using a specified offset
public class AmazingCryptoMachine {

    static Scanner scanner = new Scanner(new InputStreamReader(System.in));
    static String fileName = null;
    static String contents = null;
    static int offset = 0;

    public void start() {
        //TODO  Implemnent pickable options - like 1)encrypt, 2)decrypt 3)write to file
        // DO you know the offset? if no - options - brute force/ statistics
        System.out.println("Welcome to the amazing Cryptography Machine");

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

            } else {
                offset = scanner.nextInt();
                break;
            }
        }

        CaesarCypher cypher = new CaesarCypher();
        String encodedMessage = cypher.encrypt(contents, offset);

        System.out.println("You're enigmatic results are.. Dun dun duuunnn... \n");
        System.out.println(encodedMessage);


        scanner.close();
    }
}
