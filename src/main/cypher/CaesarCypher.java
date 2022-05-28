package main.cypher;

//assuming letters are lowercase and only spaces no special characters.
//Modulo ensures any positive number can be used

public class CaesarCypher {

    public String encrypt(String message, int offSet) {

        StringBuilder result = new StringBuilder();

        for (char character : message.toCharArray()) {
            if (character != ' ') {
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

    String decrypt(String message, int offset) {
        return encrypt(message, 26 - (offset % 26));

    }
}
