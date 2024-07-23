package ca.nl.cna.quintin.java2.Assignments.Assignment5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.net.URL;

/**
 * Periodically checks an RSS feed and adds new items to an RSS feed writer.
 */
public class RSSFeedChecker implements Runnable {
    private String URL;
    private RSSFeedWriter feedWriter;

    /**
     * Constructs an RSSFeedChecker with the specified URL and RSSFeedWriter.
     *
     * @param URL the URL of the RSS feed to check
     * @param feedWriter the RSSFeedWriter to add new items to
     */
    public RSSFeedChecker(String URL, RSSFeedWriter feedWriter) {
        this.URL = URL;
        this.feedWriter = feedWriter;
    }

    /**
     * Runs the RSS feed checker. Continuously checks the RSS feed for new items.
     */
    @Override
    public void run() {
        while (true) {
            try {
                checkRSSFeed();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * Checks the RSS feed for new items and adds them to the feed writer.
     * Only processes the first three items in the feed.
     */
    public void checkRSSFeed() {
        try {
            URL url = new URL(URL);
            // XML Document building
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            // Process XML
            NodeList itemList = doc.getElementsByTagName("item");

            for (int i = 0; i < 3; i++) {
                Node itemNode = itemList.item(i);
                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element itemElement = (Element) itemNode;
                    String title = itemElement.getElementsByTagName("title").item(0).getTextContent();
                    String link = itemElement.getElementsByTagName("link").item(0).getTextContent();
                    String pubDate = itemElement.getElementsByTagName("pubDate").item(0).getTextContent();
                    feedWriter.addRSSItem(new RSSItem(title, link, pubDate));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
