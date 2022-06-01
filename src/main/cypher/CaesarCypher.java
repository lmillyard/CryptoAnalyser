package main.cypher;

//Algorithm for the Caesar cipher - Skips all special characters and capitals.
//Modulo ensures any number can be used

public class CaesarCypher {


    public String encrypt(String message, int offSet) {

        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (character > 96 && character < 123) {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offSet) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    public String decrypt(String message, int offset) {
        return encrypt(message, 26 - (offset % 26));

    }

}
