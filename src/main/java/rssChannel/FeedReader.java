package rssChannel;

import java.net.URL;
import java.util.ArrayList;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;


public class FeedReader {
    private static String reportURL = "http://report.if.ua/feed/";//news site report.if.ua
    private static String canal24URL = "https://24tv.ua/rss/all.xml";//news site report.if.ua
    private static String gkpressURL = "http://gk-press.if.ua/feed/";//news site gk-press.if.ua


/*Вам потрібно переглянути всі URL-адреси на своєму веб-сайті, а потім знайти файл, який містить "rss".

Метод вище, можливо, не буде працювати в деяких випадках, якщо URL-адресу в тезі href виглядає приблизно як feed.xml ,
тому в цьому випадку вам потрібно буде пропустити все теги, що містять href І rss, а потім просто проаналізувати URL-адресу
з атрибута href.

Якщо ви хочете зробити це через браузер, натисніть CTRL + U, щоб переглянути джерело, потім CTRL + F, щоб відкрити вікно
пошуку, а потім просто введіть rss . URL-адресу RSS-каналу повинен з'явитися негайно.
*/
public static ArrayList <SyndEntry> getPapers() {
    boolean ok = false;
    ArrayList <SyndEntry> papers = new ArrayList<>();
    try {
        URL feedUrl = new URL(gkpressURL);

        SyndFeedInput input = new SyndFeedInput();
        XmlReader xmlReader = new XmlReader(feedUrl);
        SyndFeed feed = input.build(xmlReader);
        papers = (ArrayList<SyndEntry>) feed.getEntries();
        ok = true;
    }
    catch (Exception ex) {
        ex.printStackTrace();
        System.out.println("ERROR: "+ex.getMessage());
        return null;
    }

    if (!ok) {
        System.out.println();
        System.out.println("FeedReader reads and prints any RSS/Atom feed type.");
        System.out.println("The first parameter must be the URL of the feed to read.");
        System.out.println();
    }
    return papers;
    }
}
