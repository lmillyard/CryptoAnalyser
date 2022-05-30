package main.interactives;

// Implements the Menu class and funnels through to correct option - encrypt/decrypt.

//TODO  Maybe give the option of encrypting a sentence from console as well as a file?
//TODO Make an option to loop back around once finished.
//TODO - could give the options of doing multiple files at once?
public class AmazingCryptoMachine {


    public void start() {
        System.out.println("Welcome to the amazing cryptography machine");
        Menu menu = new Menu();
        int option = menu.pickAnOption();
        // Option 1 = encrypt, Option 2 = decrypt, Option 0 = exit.
        while(true) {
            if(option == 0) {
                break;
            }
            if(option == 1) {
                Encrypt encrypt = new Encrypt();
                encrypt.start();
                break;
            } else if (option == 2) {
                Decrypt decrypt = new Decrypt();
                decrypt.start();
                break;
            }
        }


}
}
