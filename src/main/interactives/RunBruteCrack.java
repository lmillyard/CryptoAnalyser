package main.interactives;

import main.cypher.BruteForce;
import main.cypher.CaesarCypher;

//Runs the Brute Crack code and outputs an interactive menu to the user asking if they want to save the decrypted message

public class RunBruteCrack extends Menu {
    private final String contents;
    private String uploadedFileContents;
    private boolean usingDefaultRules;
    private int probableKey;

    public RunBruteCrack(String fileContents, boolean defaultRules){
        this.contents = fileContents;
        this.usingDefaultRules = defaultRules;
    }


    public void run(String language) {

        if (usingDefaultRules) {
            BruteForce brute = new BruteForce(contents);
            probableKey = brute.bruteCrack(language, usingDefaultRules);
        } else {
            uploadedFileContents = pickAFile();
            BruteForce brute = new BruteForce(contents, uploadedFileContents);
            probableKey = brute.bruteCrack(language, usingDefaultRules);
        }
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

        System.out.println("My best guess is that " + probableKey + " is the key.");
        System.out.println("lets test it out!\n");

        CaesarCypher cypher = new CaesarCypher();
        String decryptedMessage = cypher.decrypt(contents, probableKey);

        System.out.println("Here is the decrypted message!\n------------------------------\n" + decryptedMessage + "\n");
        Menu menu = new Menu();
        menu.saveFile(decryptedMessage);
    }
}
