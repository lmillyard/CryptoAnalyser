package main.interactives;

import main.cypher.CaesarCypher;
import main.cypher.StatisticalCrack;
import main.fileOperations.WriteFile;

import java.io.InputStreamReader;
import java.util.Scanner;

public class RunStatisticalDecrypt {
    String contents;
    private static Scanner scanner = new Scanner(new InputStreamReader(System.in));

    public RunStatisticalDecrypt(String fileContents){
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
        System.out.println("Shall we save your results, yes or no?");
// checks for valid input and saves a text file.
        while(true) {
            String saveChoice = scanner.nextLine();
            if(saveChoice.equalsIgnoreCase("yes")){

                System.out.println("Great what shall we name our file?");

                String newFile = scanner.nextLine();
                WriteFile writer = new WriteFile();

                writer.writeToFile(newFile, decryptedMessage);

                System.out.println("File: \"" + newFile + "\" saved!");
                break;

            } else if (saveChoice.equalsIgnoreCase("no")) {
                System.out.println("Excellent, moving on!");
                break;
            } else {
                System.out.println("please enter yes or no!");
            }
        }
    }
}
