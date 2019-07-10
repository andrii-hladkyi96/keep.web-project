package telegramBot;

import com.rometools.rome.feed.synd.SyndEntry;
import rssChannel.FeedReader;

import java.util.ArrayList;
import java.util.Iterator;

public class News{
    public static ArrayList<String> getNews() {
        FeedReader feedReader = new FeedReader();
        ArrayList <SyndEntry> newsCollection = feedReader.getPapers();
        ArrayList <String> news = new ArrayList<>();
        Iterator iterator = newsCollection.iterator();
        while(iterator.hasNext()) {
            SyndEntry syndEntry = (SyndEntry) iterator.next();
            news.add(syndEntry.getTitle() + "\n" + syndEntry.getLink());
        }
        return news;
    }


}
