package ca.nl.cna.quintin.java2.Assignments.Assignment3.Book.Q1405;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomSentences {

    public static Map<String, String[]> WORDS = new HashMap<>();

    static{
        WORDS.put("article", new String[]{"the","a","one","some","any"});
        WORDS.put("noun", new String[]{"boy","girl","dog","town","car"});
        WORDS.put("verb", new String[]{"drove","jumped","ran","walked","skipped"});
        WORDS.put("preposition", new String[]{"to", "from","over","under","on"});
    }

    public static String[] ORDER = {"article", "noun", "verb", "preposition", "article", "noun"};

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            String sentence = generateSentence();
            System.out.println(sentence);
        }
    }

    public static String generateSentence() {
        Random random = new Random();
        StringBuilder sentence = new StringBuilder();
        String[] wordArray;
        for (String s : ORDER) {
            wordArray = WORDS.get(s);
            sentence.append(wordArray[random.nextInt(wordArray.length)]).append(" ");
        }
        sentence.deleteCharAt(sentence.length() -1);
        sentence.append(".");

        String output = sentence.toString();
        output = Character.toUpperCase(output.charAt(0)) + output.substring(1);

        return output;
    }

}
