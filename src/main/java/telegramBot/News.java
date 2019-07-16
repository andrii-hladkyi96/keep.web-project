package telegramBot;

import com.rometools.rome.feed.synd.SyndEntry;
import rssChannel.FeedReader;

import java.util.*;

public class News{

    public static LinkedList<String> getNews(String URL) {
        FeedReader feedReader = new FeedReader();
        LinkedList <SyndEntry> newsBlock = new LinkedList<>();

        newsBlock.addAll(feedReader.getNewsBlock(URL));

        LinkedList<String> news = new LinkedList<>();

        Iterator <SyndEntry> iterator = newsBlock.iterator();
        while(iterator.hasNext()) {
            SyndEntry syndEntry = iterator.next();
            news.offer(syndEntry.getTitle() + "\n" + syndEntry.getLink());
        }
        return news;
    }
}
