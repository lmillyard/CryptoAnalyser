package main.cypher;

import java.util.LinkedHashMap;
import java.util.Map;

//Class that call in LanguageRules and loops through them to create a score based on most probable offset
//The Highest score wins. Returns the most likely Offset based on this score.
public class BruteForce {

    private String contents;
    private String uploadedContents;

    Map<Integer, Integer> scoreMap = new LinkedHashMap<>();
    Integer highestScore;
    int winningOffset;

    public BruteForce(String contents){
        this.contents = contents;
    }
    public BruteForce(String contents, String userUploadedContents){
        this.contents = contents;
        this.uploadedContents = userUploadedContents;
    }

    public int bruteCrack(String language, boolean defaultRule) {

        CaesarCypher cypher = new CaesarCypher();
        LanguageRules languageRules = new LanguageRules(language, defaultRule, uploadedContents);

        for (int offset = 0; offset < 26; offset++) {
            int score = 0;
            String decipheredMessage = cypher.decrypt(contents, offset);
            String[] messageArray = decipheredMessage.split(" ");

            for (String word: messageArray) {
                score += languageRules.getScore(word);
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
