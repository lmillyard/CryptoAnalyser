package main.interactives;

import main.cypher.BruteForce;
import main.cypher.CaesarCypher;

import java.io.InputStreamReader;
import java.util.Scanner;

//Runs the Brute Crack code and outputs an interactive menu to the user asking if they want to save the decrypted message

public class RunBruteCrack {
    String contents;
    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public RunBruteCrack(String fileContents){
        this.contents = fileContents;
    }


    public void run() {

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

        BruteForce brute = new BruteForce(contents);
        int probableKey = brute.bruteCrack();

        System.out.println("My best guess is that " + probableKey + " is the key.");
        System.out.println("lets test it out!\n");

        CaesarCypher cypher = new CaesarCypher();
        String decryptedMessage = cypher.decrypt(contents, probableKey);

        System.out.println("Here is the decrypted message!\n------------------------------\n" + decryptedMessage + "\n");
        Menu menu = new Menu();
        menu.saveFile(decryptedMessage);
    }
}
