package ca.nl.cna.quintin.java2.Assignments.Assignment5;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class RSSFeedChecker implements Runnable{
    private String URL;
    private RSSFeedWriter feedWriter;

    public RSSFeedChecker(String URL, RSSFeedWriter feedWriter) {
        this.URL = URL;
        this.feedWriter = feedWriter;
    }

    @Override
    public void run() {
        while(true) {
            try{
                checkRSSFeed();
            }
            catch(Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public void checkRSSFeed(){
        try{
            java.net.URL url = new URL(URL);
            //XML Document building
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url.openStream());

            //This is how you work with XML - you do not need to modify this!
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

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


}
