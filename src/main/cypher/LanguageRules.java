package main.cypher;

public class LanguageRules {

//    private String language;
    private String cypheredWord;


//    public LanguageRules(String language){
//        this.language = language;
//    }
    // int score - add points for "the, and..." subtract point for "zz,yy,uu..."


//    public void start(String word) {
//        this.cypheredWord = word;
//        if (language.equalsIgnoreCase("english")) {
//            englishLangCipherScore(cypheredWord);
//        }
//    }


    // Rules for English.
    public int englishLangCipherScore(String wordToCheck) {
        int score = 0;

        String cannotExist [] = new String[] {"bx", "fq", "pz", "zk", "qp", "bx", "cj", "qw", "gx", "cj", "sx", "vf", "rk",};
        String mustExist [] = new String[] {"the", "and", "if", "of", "a", "in", "is", "you", "that", "it", "he", "she"};

        for (String combo: cannotExist) {
            if(wordToCheck.contains(combo)) {
                score -= 50;
            }
        }

        for (String word: mustExist) {
            if(wordToCheck.equalsIgnoreCase(word)){
                score+=50;
            }
        }

        return score;

        }


}
