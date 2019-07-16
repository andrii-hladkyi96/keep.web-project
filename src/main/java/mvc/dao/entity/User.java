package mvc.dao.entity;

import java.util.Objects;

public class User{
    private long id;
    private long chat_id;
    private String firstname;
    private String category;
    private String feedURL;
    private long interval;
    private long numberOfNews;

    public User(long id,long chat_id, String firstname, String category, String feedURL, long interval, long numberOfNews) {
        this.id = id;
        this.chat_id =chat_id;
        this.firstname = firstname;
        this.category = category;
        this.feedURL = feedURL;
        this.interval = interval;
        this.numberOfNews = numberOfNews;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFeedURL() {
        return feedURL;
    }

    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public long getNumberOfNews() {
        return numberOfNews;
    }

    public void setNumberOfNews(long numberOfNews) {
        this.numberOfNews = numberOfNews;
    }

    public void setNumberOfNews(int numberOfNews) {
        this.numberOfNews = numberOfNews;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", firstname='" + firstname + '\'' +
                ", category='" + category + '\'' +
                ", feedURL='" + feedURL + '\'' +
                ", interval=" + interval +
                ", numberOfNews=" + numberOfNews +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                chat_id == user.chat_id &&
                interval == user.interval &&
                numberOfNews == user.numberOfNews &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(category, user.category) &&
                Objects.equals(feedURL, user.feedURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chat_id, firstname, category, feedURL, interval, numberOfNews);
    }
}
