package ir.hassannasr.majles.crowling;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

/**
 * Created by hassan on 09/12/2015.
 */
public class MemberCrawler {
    public static void main(String[] args) throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream("./namaLink.txt", true));
        for (int i = 172; i <= 172; i++) {
//            URL url = new URL(String.format("http://rc.majlis.ir/fa/parliament_member?page=%d&lu_period_no=33", i));
            URL url = new URL(String.format("http://rc.majlis.ir/fa/parliament_member?page=%d", i));
            Document doc = Jsoup.parse(url, 2000);

            Elements resultLinks = doc.select("a");
            for (Element link : resultLinks) {
                String href = link.attr("href");
                if (href.contains("parliament_member/show")) {
                    out.println(href);
                    System.out.println(link.text() + " : " + href);
                }
//                System.out.println("href: " + href);
            }
        }
    }
}
