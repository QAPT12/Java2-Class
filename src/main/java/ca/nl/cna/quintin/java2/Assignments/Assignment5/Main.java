package ca.nl.cna.quintin.java2.Assignments.Assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Application class that initializes the RSS feed checker and writer to write RSSItems to the console.
 */
public class Main {

    /**
     * The main method that runs the application.
     * It prompts the user to enter RSS feed URLs, starts threads to check the feeds, and writes new items.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            List<String> feedUrls = new ArrayList<>();
            final String end = "done";
            String url = "";

            RSSFeedWriter feedWriter = new RSSFeedWriter();

            System.out.println("Enter RSS feed URLs (type 'done' to finish):");
            while (!url.equals(end)) {
                url = scanner.nextLine();
                if (!url.equalsIgnoreCase(end)) {
                    feedUrls.add(url);
                }
            }

            for (String feedUrl : feedUrls) {
                Thread feedThread = new Thread(new RSSFeedChecker(feedUrl, feedWriter));
                feedThread.start();
            }
        }
    }
}
