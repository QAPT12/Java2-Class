package ca.nl.cna.quintin.java2.Assignments.Assignment3.Book.Q1409;

public class ReversedSentence {

    public static String sentence = "this is a sentence to be reversed";

    public static void main(String[] args) {
        System.out.println(reverseSentence(sentence));
    }

    public static String reverseSentence(String sentence){
        String[] words = sentence.split("\\s");
        StringBuilder reverse = new StringBuilder();

        for (int i = words.length - 1; i >= 0 ; i--) {
            reverse.append(words[i]).append(" ");
        }
        reverse.deleteCharAt(reverse.length() - 1);
        return reverse.toString();
    }
}
