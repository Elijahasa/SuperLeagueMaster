package ntv.upgrade.superleaguemaster.NewsFeed;

/**
 * Created by jfrom on 4/27/2016.
 */
public class OpenLeagueItem{

    public OpenLeagueItem() {

    }

    private  int eventTypeImage;
    private String eventTitle, eventDate, eventLocation, eventDescription, eventPrice;

    public int getEventTypeImage() {
        return eventTypeImage;
    }

    public void setEventTypeImage(int eventTypeImage) {
        this.eventTypeImage = eventTypeImage;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventPrice() {
        return eventPrice;
    }

    public void setEventPrice(String eventPrice) {
        this.eventPrice = eventPrice;
    }
}