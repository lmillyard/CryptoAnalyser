package main.interactives;

// Implements the Menu class and funnels through to correct option - encrypt/decrypt.

//TODO  Maybe give the option of encrypting a sentence from console as well as a file?
//TODO - could give the options of doing multiple files at once?
public class AmazingCryptoMachine extends Menu{
    private int timesRoundCounter = 0;
    private String contents;


    private void welcomeMessage() {
        System.out.println("""
                \t~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                Welcome to the amazing crypto machine!
                \t~~~~~~~~~~~~~~~~~~~~~~~~~~~~""");
    }
    private void welcomeBackMessage(){
        System.out.println("\t\tWelcome back!");
    }
    private void goodbyeMessage() {
        System.out.println("\tThank you and goodbye!");
    }

    public void start() {

        if (timesRoundCounter == 0) {
            welcomeMessage();
            timesRoundCounter++;
        }else {
            welcomeBackMessage();
        }

            int option = pickAnOption();
            // Option 1 = encrypt, Option 2 = decrypt, Option 0 = exit.
            switch (option) {
                case 0:
                    goodbyeMessage();
                    break;
                case 1:
                    contents = pickAFile();
                    int offset = pickAnOffset();
                    encrypt(contents, offset);
                    start();
                case 2:
                    contents = pickAFile();
                    decrypt(contents);
                    start();
            }
    }
}
