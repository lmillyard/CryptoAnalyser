package main.interactives;

// Implements the Menu class and funnels through to correct option - encrypt/decrypt.

//TODO  Maybe give the option of encrypting a sentence from console as well as a file?
//TODO Make an option to loop back around once finished.
//TODO - could give the options of doing multiple files at once?
public class AmazingCryptoMachine extends Menu{

    private static int timesRoundCounter = 0;

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
        while (true) {
            if (option == 0) {
                goodbyeMessage();
                break;
            }
            if (option == 1) {
                String contents = pickAFile();
                int offset = pickAnOffset();
                encrypt(contents, offset);
                timesRoundCounter++;
                start();
            } else if (option == 2) {
                String contents = pickAFile();
                decrypt(contents);
                timesRoundCounter++;
                start();
            }
        }
    }
}
