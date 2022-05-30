package main.cypher;

import java.util.LinkedHashMap;
import java.util.Map;

public class BruteForce {

    String contents;

    Map<Integer, Integer> scoreMap = new LinkedHashMap<>();
    Integer highestScore;
    int winningOffset;

    public BruteForce(String contents){
        this.contents = contents;

    }

    public int bruteCrack() {

        CaesarCypher cypher = new CaesarCypher();
        LanguageRules languageRules = new LanguageRules();

        for (int offset = 0; offset < 26; offset++) {
            int score = 0;
            String decipheredMessage = cypher.decrypt(contents, offset);
            String messageArray [] = decipheredMessage.split(" ");

            for (String word: messageArray) {
                score += languageRules.englishLangCipherScore(word);
            }
            scoreMap.put(offset,score);
        }

        highestScore = (Integer) scoreMap.values().toArray()[0];

        for (Map.Entry<Integer, Integer> entry: scoreMap.entrySet()) {
            if(entry.getValue() > highestScore) {
                highestScore = entry.getValue();
                winningOffset = entry.getKey();
            }
        } return winningOffset;
    }


}
