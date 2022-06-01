package main.cypher;

/*Language rules - Currently only for English - but has scope to expand this at a later stage(See commented out code for example).
Rules are based on 'illegal' letter combinations and the 10 most common words in the english language.
each word is checked against both list's, and it returns a score based upon this*/

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

        String cannotExist [] = new String[] {"bx", "df", "fq", "pz", "zk", "qp", "bx", "cj", "qw", "gx", "cj", "sx", "vf", "rk",};
        String mustExist [] = new String[] {"the", "and", "if", "of", "a", "in", "is", "you", "that", "it", "he", "she"};

        for (String combo: cannotExist) {
            if(wordToCheck.contains(combo)) {
                score -= 100;
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
