package ca.nl.cna.quintin.java2.Assignments.Assignment3.Redacted;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Redactor {

    public static void main(String[] args) {
        Path inputFile = Paths.get("src/main/java/ca/nl/cna/quintin/java2/Assignments/Assignment3/Redacted/sampleInfo.txt");
        Path outputFile = Paths.get("src/main/java/ca/nl/cna/quintin/java2/Assignments/Assignment3/Redacted/sampleInfoRedactedInfo.txt");

        Pattern datePattern = Pattern.compile("\\d{2}-\\d{2}-\\d{2,4}");
        Pattern creditCardPattern = Pattern.compile("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
        Pattern currencyPattern = Pattern.compile("\\$\\d+(\\.\\d{1,2})?");
        Pattern codePattern = Pattern.compile("CODE\\d+");

        try (
         BufferedReader read = Files.newBufferedReader(inputFile);
         BufferedWriter write = Files.newBufferedWriter(outputFile);
        ) {
            String line;
            while((line = read.readLine()) != null){
                line = datePattern.matcher(line).replaceAll("##-##-####");
                line = creditCardPattern.matcher(line).replaceAll("####-####-####-####");
                line = currencyPattern.matcher(line).replaceAll("\\$##.##");
                line = codePattern.matcher(line).replaceAll("CODE################");

                write.write(line + "\n");
            }
            System.out.println("File redacted and saved to sampleInfoRedactedInfo.txt");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
