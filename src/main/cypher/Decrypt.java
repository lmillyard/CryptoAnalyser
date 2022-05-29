package main.cypher;
//Output to console: uses the CaesarCypher/Break class to decrypt a given file and outputs options to the user asking whether they
//know the offset key or need to crack it - returns the cracked code.
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
                System.out.println("Hmmm, Okay lets try cracking it then!\n");

                try {
                    for (int i = 0; i < 4; i++) {
                        Thread.sleep(500);
                        System.out.print("**Thinking**");
                    }
                    Thread.sleep(500);
                    System.out.println("\n");

                } catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e){
//                    System.out.println(e.getMessage());
//                }
//                System.out.println("\n************ Thinking *************\n");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e){
//                    System.out.println(e.getMessage());
//                }
                BruteForce breaker = new BruteForce();
                int probableKey = breaker.breakCipher(contents);
                System.out.println("My best guess is that " + probableKey + " is the key.");
                System.out.println("lets test it out!\n");
                CaesarCypher cypher = new CaesarCypher();
                String decryptedMessage = cypher.decrypt(contents, probableKey);

                System.out.println("Here is the decrypted message!\n\n" + decryptedMessage + "\n");
                break;
            } else {
                System.out.println("please enter yes or no!");
            }

        }
    }

}
