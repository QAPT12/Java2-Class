package ca.nl.cna.quintin.java2.Assignments.Assignment2.Book.Q1509;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhishingFinder {

    private static final Map<String, Integer> PHISHING_WORDS = new HashMap<>();

    public static void main(String[] args) {
        String phishingWords = "src/main/java/ca/nl/cna/quintin/java2/Assignments/Assignment2/Book/Q1509/PhishingWords.txt";
        String emailPath = "src/main/java/ca/nl/cna/quintin/java2/Assignments/Assignment2/Book/Q1509/Email.txt";

        try{
            loadPhishingWords(phishingWords);

            Map<String, Integer> occurrences = scanEmail(emailPath);
            int totalPoints = 0;

            System.out.println("Keyword or Phrase   | Occurrences | Points");
            System.out.println("------------------------------------------");
            for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
                String word = entry.getKey();
                int count = entry.getValue();
                int points = count * PHISHING_WORDS.get(word);
                totalPoints += points;
                System.out.printf("%-20s | %-11d | %d%n", word, count, points);
            }

            // Print total points for the entire message
            System.out.println("\nTotal Phishing Points: " + totalPoints);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public static void loadPhishingWords(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] readLine = line.split(",");
                if(readLine.length == 2) {
                    String word = readLine[0].trim();
                    int score = Integer.parseInt(readLine[1].trim());
                    PHISHING_WORDS.put(word, score);
                }
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    public static Map<String, Integer> scanEmail(String path) {
        Map<String, Integer> occurrence = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while((line = reader.readLine()) != null) {
                line = line.toLowerCase();

                for (String term : PHISHING_WORDS.keySet()) {
                    int index = line.indexOf(term.toLowerCase());
                    while(index != -1) {
                        occurrence.put(term, occurrence.getOrDefault(term, 0) + 1);
                        index = line.indexOf(term.toLowerCase(), index +1);
                    }
                }
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return occurrence;
    }
}
