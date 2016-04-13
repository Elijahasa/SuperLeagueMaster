package ntv.upgrade.superleaguemaster.NewsFeed;

/**
 * Created by Paulino on 10/9/2015.
 */
public class NewsFeedItem {

    public int image, newsID;
    public String tittle;
    public String description;

    public NewsFeedItem(int image, String tittle) {
        this.image = image;
        this.tittle = tittle;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }
}
