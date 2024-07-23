package ca.nl.cna.quintin.java2.Assignments.Assignment5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RSSFeedWriter {
    private Queue<RSSItem> RSSQueue = new LinkedList<>();
    private List<RSSItem> RSSList = new ArrayList<>();

    public synchronized void addRSSItem(RSSItem item) throws InterruptedException {
        RSSQueue.add(item);
        notifyAll();
        writeFeed();
    }

    public synchronized void writeFeed() throws InterruptedException {
        while(RSSQueue.isEmpty()) {
            wait();
            System.out.println("Waiting on queue");
        }
        RSSItem item = RSSQueue.poll();
        if (RSSList.contains(item)){
            // if we already have this item just ignore and do nothing
        } else {
            RSSList.add(item);
            System.out.println("Title: " + item.getTitle());
            System.out.println("Link: " + item.getLink());
            System.out.println("Published Date: " + item.getPubDate());
            System.out.println();
        }
    }
}
