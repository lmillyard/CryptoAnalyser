package main.cypher;

/*Language rules - Currently only for English - but has scope to expand this at a later stage.
Rules are based on 'illegal' letter combinations and the 10 most common words in the english language.
each word is checked against both list's, and it returns a score based upon this*/

import main.fileOperations.ReadFile;
import main.interactives.Menu;

public class LanguageRules extends Menu {

    private final String language;
    private int score;
    private final boolean isUsingDefaultRules;



    public LanguageRules(String language, boolean defaultRules){
        this.language = language;
        this.isUsingDefaultRules = defaultRules;
    }

    public int getScore(String wordToCheck) {
        score = 0;
        if (language.equalsIgnoreCase("english") && isUsingDefaultRules) {
            score = englishLangDefaultScore(wordToCheck);
        }

        return score;
    }

    private String[] splitCSV(String fileName) {
        ReadFile readFile = new ReadFile(fileName);
        String contents = readFile.readFile();

        return contents.split(",");

    }
    private int englishLangDefaultScore(String wordToCheck) {
        String[] cannotExist = splitCSV("englishLangIllegalCombos");
        String[] mustExist = splitCSV("englishLangMostCommon");

        for (String combo : cannotExist) {
            if (wordToCheck.contains(combo)) {
                score -= 100;
            }
        }

        for (String word : mustExist) {
            if (wordToCheck.equalsIgnoreCase(word)) {
                score += 50;
            }
        }
        return score;
    }

//    public int languageCipherScore(String wordToCheck) {
//
//        int score = 0;
//        String[] cannotExist = splitCSV("englishLangIllegalCombos");
//        String[] mustExist = splitCSV("englishLangMostCommon");
//
//        boolean defaultRules = pickDefaultRules(language);
//
//
//            for (String combo : cannotExist) {
//                if (wordToCheck.contains(combo)) {
//                    score -= 100;
//                }
//            }
//
//            for (String word : mustExist) {
//                if (wordToCheck.equalsIgnoreCase(word)) {
//                    score += 50;
//                }
//            }
//        return score;
//    }

}
