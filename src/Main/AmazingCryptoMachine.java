package Main;

import java.io.InputStreamReader;
import java.util.Scanner;

// Interactive menu for encoding a given file (from console) then encoding using a specified offset
public class AmazingCryptoMachine {

    public void start() {

        System.out.println("Welcome to the amazing Cryptography Machine");
        System.out.println("Pick a file to encrypt");

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String fileName = scanner.nextLine();


        ReadFile reader = new ReadFile();
        String contents = reader.readFile(fileName);

        System.out.println("The current contents of your file are: \n *****Start of File***** \n" + contents);
        System.out.println("*****End of File***** \n");

        System.out.println("We are going to be using the super impressive Caesar cypher... Cue Oohhhs and aahhhhhs! \n");
        System.out.println("Pick a number any number - we will enter this into the machine and see what it come ups with.");

        int offset = scanner.nextInt();
        CaesarCypher cypher = new CaesarCypher();
        String encodedMessage = cypher.cypher(contents, offset);

        System.out.println("You're enigmatic results are.. Dun dun duuunnn... \n");
        System.out.println(encodedMessage);



    }
}
