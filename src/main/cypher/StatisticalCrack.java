package main.cypher;
//Cracking a Caesar cipher, by using ChiSquares and the probability statistics of each occurrence of a letter.
//code loops through each offset 0-25 and each letter gets an error score - the lowest one corresponds with the
//most likely offset - this number is returned

import org.apache.commons.math3.stat.inference.ChiSquareTest;

import java.util.Arrays;
import java.util.stream.IntStream;


public class StatisticalCrack {

    CaesarCypher cypher = new CaesarCypher();

    private static final double[] englishLettersProbabilities = {0.073, 0.009, 0.030, 0.044, 0.130, 0.028, 0.016, 0.035, 0.074,
            0.002, 0.003, 0.035, 0.025, 0.078, 0.074, 0.027, 0.003,
            0.077, 0.063, 0.093, 0.027, 0.013, 0.016, 0.005, 0.019, 0.001};

    public int breakCipher(String message) {
        return probableOffset(chiSquares(message));
    }

    private double[] chiSquares(String message) {
        double[] expectedLettersFrequencies = expectedLettersFrequencies(message.length());

        double[] chiSquares = new double[26];

        for (int offset = 0; offset < chiSquares.length; offset++) {
            String decipheredMessage = cypher.decrypt(message, offset);
            long[] lettersFrequencies = observedLettersFrequencies(decipheredMessage);
            double chiSquare = new ChiSquareTest().chiSquare(expectedLettersFrequencies, lettersFrequencies);
            chiSquares[offset] = chiSquare;
        }

        return chiSquares;
    }

    private long[] observedLettersFrequencies(String message) {
        return IntStream.rangeClosed('a', 'z')
                .mapToLong(letter -> countLetter((char) letter, message))
                .toArray();
    }

    private long countLetter(char letter, String message) {
        return message.chars()
                .filter(character -> character == letter)
                .count();
    }

    private double[] expectedLettersFrequencies(int messageLength) {
        return Arrays.stream(englishLettersProbabilities)
                .map(probability -> probability * messageLength)
                .toArray();
    }

    private int probableOffset(double[] chiSquares) {
        int probableOffset = 0;
        for (int offset = 0; offset < chiSquares.length; offset++) {
            //System.out.println(String.format("Chi-Square for offset %d: %.2f", offset, chiSquares[offset]));
            if (chiSquares[offset] < chiSquares[probableOffset]) {
                probableOffset = offset;
            }
        }

        return probableOffset;
    }
}
