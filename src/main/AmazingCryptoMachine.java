package main;

import main.cypher.CaesarCypher;
import main.cypher.Decrypt;
import main.cypher.Encrypt;
import main.cypher.Menu;
import main.fileOperations.ReadFile;
import main.fileOperations.WriteFile;

import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

//TODO  Implemnent pickable options - like 1)encrypt, 2)decrypt 3)write to file
// DO you know the offset? if no - options - brute force/ statistics
// Interactive menu for encoding a given file (from console) then encoding using a specified offset
public class AmazingCryptoMachine {


    public void start() {
        System.out.println("Welcome to the amazing cryptography machine");
        main.cypher.Menu menu = new Menu();
        int option = menu.pickAnOption();
        // Option 1 = encrypt, Option 2 = decrypt
        while(true) {
            if(option == 0) {
                break;
            }
            if(option == 1) {
                Encrypt encrypt = new Encrypt();
                encrypt.start();
            } else if (option == 2) {
                Decrypt decrypt = new Decrypt();
                decrypt.start();
            }
        }


}
}
