package telegramBot;

import com.rometools.rome.feed.synd.SyndEntry;
import rssChannel.FeedReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class News{
    private static String reportURL = "http://report.if.ua/feed/";//news site report.if.ua
    private static String gkpressURL = "http://gk-press.if.ua/feed/";//news site gk-press.if.ua
    private static String pravdaURL = "https://pravda.if.ua/sample-page/feed/";
    private static String zaxidURL = "https://zaxid.net/rss/1.xml";


    public static LinkedList<String> getNews() {
        FeedReader feedReader = new FeedReader();
        LinkedList <SyndEntry> newsBlock = new LinkedList<>();
        //newsBlock.addAll(feedReader.getNewsBlock(reportURL));
        //newsBlock.addAll(feedReader.getNewsBlock(pravdaURL));
        //newsBlock.addAll(feedReader.getNewsBlock(zaxidURL));
        newsBlock.addAll(feedReader.getNewsBlock(gkpressURL));



        LinkedList<String> news = new LinkedList<>();

        Iterator <SyndEntry> iterator = newsBlock.iterator();
        while(iterator.hasNext()) {
            SyndEntry syndEntry = iterator.next();
            news.offer(syndEntry.getTitle() + "\n" + syndEntry.getLink());
        }
        return news;
    }
}
