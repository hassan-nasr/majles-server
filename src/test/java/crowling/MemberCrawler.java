package crowling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * Created by hassan on 09/12/2015.
 */
public class MemberCrawler {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 30; i++) {
            URL url = new URL(String.format("http://rc.majlis.ir/fa/parliament_member?page=%d&lu_period_no=33", i));
            Document doc = Jsoup.parse(url, 2000);

            Elements resultLinks = doc.select("a");
            for (Element link : resultLinks) {
                String href = link.attr("href");
                if (href.contains("parliament_member/show"))
                    System.out.println(link.text() + " : " + href);
//                System.out.println("href: " + href);
            }
        }
    }
}
