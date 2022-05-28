package main.cypher;

import main.AmazingCryptoMachine;
import main.fileOperations.ReadFile;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Decrypt {

    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public void start() {

        String contents;
        while(true) {
            System.out.println("Pick a file to decrypt");
            String fileName = scanner.nextLine();

            File file = new File(fileName);

            if (file.exists()) {
                ReadFile reader = new ReadFile();
                contents = reader.readFile(fileName);
                break;
            } else {
                System.out.println("That file does not exist.");
            }
        }

        System.out.println("Do you know the key, yes/no?");

        while(true) {
            String choice = scanner.nextLine();

            if(choice.equalsIgnoreCase("yes")){

                System.out.println("Great, enter it now!");

                int offset;
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
                String decryptedMessage = cypher.decrypt(contents, offset);

                System.out.println("Here is your decrypted message! \n" + decryptedMessage);
                break;

            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("Hmmm, Okay lets try cracking it then!");
                break;
            } else {
                System.out.println("please enter yes or no!");
            }
        }
        scanner.close();
        System.out.println();
    }

}
