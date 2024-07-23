package ca.nl.cna.quintin.java2.Assignments.Assignment5;

/**
 * Represents an RSS feed item with a title, link, and publication date.
 */
public class RSSItem {

    private String title;
    private String link;
    private String pubDate;

    /**
     * Constructs an RSSItem with the specified title, link, and publication date.
     *
     * @param title the title of the RSS item
     * @param link the link to the RSS item
     * @param pubDate the publication date of the RSS item
     */
    public RSSItem(String title, String link, String pubDate) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    /**
     * Returns the title of the RSS item.
     *
     * @return the title of the RSS item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the link to the RSS item.
     *
     * @return the link to the RSS item
     */
    public String getLink() {
        return link;
    }

    /**
     * Returns the publication date of the RSS item.
     *
     * @return the publication date of the RSS item
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * Compares this RSSItem to the specified object. The result is true if and only if
     * the argument is not null and is an RSSItem object that has the same title as this object.
     *
     * @param object the object to compare this RSSItem against
     * @return true if the given object represents an RSSItem equivalent to this RSSItem, false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object.getClass() != this.getClass()) {
            return false;
        }
        return this.getTitle().equals(((RSSItem) object).getTitle());
    }
}
