package ca.nl.cna.quintin.java2.InClassPractices.May21Files;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

public class GuessProgram {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner input = new Scanner(System.in);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new java.util.Date());
        String fileName = "./logs/gamelog" + timeStamp + ".txt";
        try(Formatter output = new Formatter(fileName)){
            int correct = rand.nextInt( 1, 100);
            int guess = -99;
            int totalGuesses = 0;
            while( correct != guess ) {

                if( totalGuesses > 0) {
                    if( guess > correct ) {
                        System.out.println("Too high");
                    }
                    else {
                        System.out.println("Too low");
                    }
                }
                System.out.println("Guess number between 1 and 100");
                try {
                    guess = input.nextInt();
                    output.format("%d%n", guess);
                    totalGuesses++;
                }
                catch( NoSuchElementException e) {
                    System.out.println("You entered an invalid number, try again");
                    input.next();
                }
            }
            System.out.println("You guessed " + correct + " It took you " + totalGuesses + " guesses");
        }
        catch( FileNotFoundException | SecurityException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }
}
