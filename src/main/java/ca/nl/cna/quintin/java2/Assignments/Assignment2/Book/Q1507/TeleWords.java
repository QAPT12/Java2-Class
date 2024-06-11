package ca.nl.cna.quintin.java2.Assignments.Assignment2.Book.Q1507;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

public class TeleWords {

    // Mapping of letters to the numbers using a 2d array
    public static final char[][] LETTERS = {
            {}, // ignoring 0 and 1
            {},
            {'A', 'B', 'C'}, // this is for 2
            {'D', 'E', 'F'}, //3
            {'G', 'H', 'I'}, //4
            {'J', 'K', 'L'}, //5
            {'M', 'N', 'O'}, //6
            {'P', 'R', 'S'}, //7
            {'T', 'U', 'V'}, //8
            {'W', 'X', 'Y'}, //9
    };

    public static void generateWords(String phoneNumber, int currentIndex, char[] word, Formatter formatter) {
        if(currentIndex == phoneNumber.length()) {
            formatter.format("%s %n", new String(word));
            return;
        }

        int digit = Character.getNumericValue(phoneNumber.charAt(currentIndex));
        for(char letter : LETTERS[digit]) {
            word[currentIndex] = letter;
            generateWords(phoneNumber, currentIndex + 1, word, formatter);
        }
    }

    public static void main(String[] args) {
        // File path, change this to where you want file to go.
        String path = "src/main/java/ca/nl/cna/quintin/java2/Assignments/Assignment2/Book/Q1507/teleWords.txt";

        Scanner input = new Scanner(System.in);
        String userNumber;

        while(true) {
            System.out.println("Enter a 7 digit phone number, no space, no 0 or 1. (i.e. 83764458): ");
            userNumber = input.next();

            if (userNumber.length() != 7 || userNumber.contains("0") || userNumber.contains("1")) {
                System.out.println("Invalid number, please try again.");
            } else {
                break;
            }
        }

        try (Formatter formatter = new Formatter(path)) {
            generateWords(userNumber, 0, new char[7], formatter);
            formatter.close();
            System.out.println("All words found for number. Check teleWords.txt");
        }
        catch (FileNotFoundException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}
