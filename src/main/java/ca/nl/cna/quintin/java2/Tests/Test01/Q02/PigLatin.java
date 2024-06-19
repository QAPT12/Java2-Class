package ca.nl.cna.quintin.java2.Tests.Test01.Q02;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PigLatin {

    public static void main(String[] args) {
        File inputFile = new File("src/main/java/Q02/English.txt");
        ArrayList<String> lines = new ArrayList<>();

        try(Scanner reader = new Scanner(inputFile);
            FileWriter writer = new FileWriter("src/main/java/Q02/PigLatin.txt")){

            while(reader.hasNextLine()){
                String line = reader.nextLine();
                String[] splitWords = line.split(" ");

                StringBuilder pigLine = new StringBuilder();

                for(String word : splitWords){
                    StringBuilder pigWord = new StringBuilder(word);
                    Character firstLetter = pigWord.charAt(0);
                    pigWord.deleteCharAt(0);
                    pigWord.append(firstLetter).append("ay");
                    pigLine.append(pigWord).append(" ");
                }

                lines.add(pigLine.toString());
            }

            for(String line : lines){
                writer.write(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

}
