package ir.hassannasr.majles.bgjob;

import ir.hassannasr.majles.bgjob.rss.Feed;
import ir.hassannasr.majles.bgjob.rss.FeedMessage;
import ir.hassannasr.majles.bgjob.rss.RSSFeedParser;
import ir.hassannasr.majles.domain.post.Post;
import ir.hassannasr.majles.domain.post.PostManager;
import ir.hassannasr.majles.domain.user.User;
import ir.hassannasr.majles.domain.user.UserManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hassan on 30/01/2016.
 */
public class FetchRSSInvoker {


    @Autowired
    UserManager userManager;
    @Autowired
    private PostManager postManager;

    public FetchRSSInvoker() {
    }

    public void tick() {
        final List<User> withRssUsers = userManager.getWithRssUsers();
        for (User user : withRssUsers) {

            try {
                final Feed feed = new RSSFeedParser(user.getRss()).readFeed();
                String lastPublishLink = postManager.getLastLinkPublished(user);
                List<FeedMessage> toAdd = new ArrayList<>();
                for (FeedMessage feedMessage : feed.getMessages()) {
                    if (feedMessage.getLink().equals(lastPublishLink))
                        break;
                    else
                        toAdd.add(feedMessage);
                }
                for (int i = toAdd.size() - 1; i >= 0; i--) {
                    FeedMessage feedMessage = toAdd.get(i);
                    try {
                        Post post = new Post();
                        post.setText("<b>" + feedMessage.getTitle() + "</b><br>" + feedMessage.getDescription());
                        Date date;
                        try {
                            DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
                            date = formatter.parse(feedMessage.getPubDate());
                        } catch (ParseException e) {
                            try {
                                DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss");
                                date = formatter.parse(feedMessage.getPubDate());
                            } catch (Exception ignored) {
                                date = new Date();
                            }
                        }
                        post.setPublishDate(date);
                        post.setUser(user);
                        post.setLink(feedMessage.getLink());
                        postManager.save(post);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
