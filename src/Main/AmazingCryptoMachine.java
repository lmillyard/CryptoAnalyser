package Main;

import java.io.InputStreamReader;
import java.util.Scanner;

public class AmazingCryptoMachine {

    public void start() {

        System.out.println("Welcome to the amazing Cryptography Machine");
        System.out.println("Pick a file to encrypt");

        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        String fileName = scanner.nextLine();


        ReadFile reader = new ReadFile();
        String contents = reader.readFile(fileName);

        System.out.println("The current contents of your file are: \n" + contents);

        CaesarCypher cypher = new CaesarCypher();
    }
}
