package ir.hassannasr.majles.bgjob.rss;

/**
 * Created by hassan on 16/12/2015.
 */

import ir.hassannasr.majles.crowling.UrlContent;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.net.URL;


public class RSSFeedParser {
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";

    final String url;

    public RSSFeedParser(String feedUrl) {
        this.url = feedUrl;
    }

    public Feed readFeed() {
        try {
            String content = new UrlContent(new URL(url)).getContent();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            final Feed feed = new Feed();

            DefaultHandler handler = new DefaultHandler() {
                String state = "";
                FeedMessage currentMessage = null;
                boolean item = true;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equals("item"))
                        currentMessage = new FeedMessage();
                    state = qName;
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equals("item"))
                        feed.getMessages().add(currentMessage);
                    state = "non";
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    String content = new String(ch, start, length);
                    switch (state) {
                        case "title":
                            if (currentMessage == null) {
                                feed.setTitle(feed.getTitle() + content);
                            } else {
                                currentMessage.setTitle(currentMessage.getTitle() + content);
                            }
                            break;
                        case "link":
                            if (currentMessage == null) {
                                feed.setLink(feed.getLink() + content);
                            } else {
                                currentMessage.setLink(currentMessage.getLink() + content);
                            }
                            break;

                        case "description":
                            if (currentMessage == null) {
                                feed.setDescription(feed.getDescription() + content);
                            } else {
                                currentMessage.setDescription(currentMessage.getDescription() + content);
                            }
                            break;
                        case "pubDate":
                            if (currentMessage != null) {
                                currentMessage.setPubDate(currentMessage.getPubDate() + content);
                            }
                            break;

                    }

                }
            };
            saxParser.parse(new URL(url).openStream(), handler);
            return feed;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
