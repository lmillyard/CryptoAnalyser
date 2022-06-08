package main.interactives;

import main.cypher.CaesarCypher;
import main.cypher.StatisticalCrack;

import java.io.InputStreamReader;
import java.util.Scanner;

//Runs the statistical crack code - outputs the decrypted code and ask user if they want to save end file

public class RunStatisticalCrack extends Menu {
    String contents;
    private static final Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public RunStatisticalCrack(String fileContents){
        this.contents = fileContents;
    }
    public void run () {

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

        StatisticalCrack breaker = new StatisticalCrack();
        int probableKey = breaker.breakCipher(contents);

        System.out.println("My best guess is that " + probableKey + " is the key.");
        System.out.println("lets test it out!\n");

        CaesarCypher cypher = new CaesarCypher();
        String decryptedMessage = cypher.decrypt(contents, probableKey);

        System.out.println("Here is the decrypted message!\n------------------------------\n" + decryptedMessage + "\n");
        saveFile(decryptedMessage);
    }
}
