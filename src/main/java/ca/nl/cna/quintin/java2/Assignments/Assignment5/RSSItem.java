package ca.nl.cna.quintin.java2.Assignments.Assignment5;

public class RSSItem {

    private String title;
    private String link;
    private String pubDate;

    public RSSItem(String title, String link, String pubDate) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getPubDate() {
        return pubDate;
    }

    @Override
    public boolean equals(Object object){
        if(object == null){
            return false;
        }
        if(object.getClass() != this.getClass()){
            return false;
        }
        return this.getTitle().equals(((RSSItem) object).getTitle());
    }
}
