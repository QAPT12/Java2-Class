package ca.nl.cna.quintin.java2.Assignments.Assignment5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Handles writing RSS feed items to a list. New items are added to a queue and processed in order.
 */
public class RSSFeedWriter {
    private Queue<RSSItem> RSSQueue = new LinkedList<>();
    private List<RSSItem> RSSList = new ArrayList<>();

    /**
     * Adds an RSS item to the queue and writes it to the feed. Notifies all waiting threads.
     *
     * @param item the RSS item to be added
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    public synchronized void addRSSItem(RSSItem item) throws InterruptedException {
        RSSQueue.add(item);
        notifyAll();
        writeFeed();
    }

    /**
     * Writes the RSS feed by processing items from the queue. Waits if the queue is empty.
     *
     * @throws InterruptedException if any thread has interrupted the current thread
     */
    public synchronized void writeFeed() throws InterruptedException {
        while (RSSQueue.isEmpty()) {
            wait();
        }
        RSSItem item = RSSQueue.poll();
        if (!RSSList.contains(item)) {
            RSSList.add(item);
            System.out.println("Title: " + item.getTitle());
            System.out.println("Link: " + item.getLink());
            System.out.println("Published Date: " + item.getPubDate());
            System.out.println();
        }
    }
}
