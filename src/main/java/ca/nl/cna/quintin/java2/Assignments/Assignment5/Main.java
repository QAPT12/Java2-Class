package ca.nl.cna.quintin.java2.Assignments.Assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try(Scanner scanner = new Scanner(System.in)){
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
