package ca.nl.cna.quintin.java2.Assignments.Assignment1;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Cat {

    public static void cat(File file) throws IOException {
        RandomAccessFile input = null;
        String line = null;

        try {
            input = new RandomAccessFile(file, "r");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            if (input != null) {
                input.close();
            }
        }
    }
}
