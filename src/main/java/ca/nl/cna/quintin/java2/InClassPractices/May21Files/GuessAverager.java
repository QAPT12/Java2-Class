package ca.nl.cna.quintin.java2.InClassPractices.May21Files;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GuessAverager {

    public static void main(String[] args) {
        Path path = Paths.get("./logs");
        if (Files.exists(path)){
            System.out.println("folder exists");

            try{
                DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
                int totalFiles = 0;
                int totalGuesses = 0;
                for (Path file : directoryStream){
                    totalFiles++;
                    System.out.println(file);
                    Scanner input = new Scanner(file);
                    while(input.hasNext()) {
                        totalGuesses++;
                        String line = input.nextLine();
                        System.out.println(line);
                    }
                }
                System.out.println("Total Guesses: " + totalGuesses);
                System.out.println("Total Files: " + totalFiles);
                System.out.println("Average Guesses: " + (float)totalGuesses/totalFiles);
            }
            catch (IOException e){
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }

}
