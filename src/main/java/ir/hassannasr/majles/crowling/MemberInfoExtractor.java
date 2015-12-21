package ir.hassannasr.majles.crowling;

import org.codehaus.jackson.map.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import static ir.hassannasr.majles.crowling.Utils.persianNormalize;

/**
 * Created by hassan on 09/12/2015.
 */
public class MemberInfoExtractor {
    public static String baseUrl = "http://rc.majlis.ir/";
    public static String saveImageLocation = "./images/";

    public static void main(String[] args) throws IOException {
        Scanner cin = new Scanner(new File("./output/member.txt"));
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayList<Member> res = new ArrayList<>();
        int counter = 0;
        while (cin.hasNextLine()) {
            System.out.println("counter = " + counter++);
            String link = cin.nextLine().split(":")[1].trim();

            URL url = new URL(baseUrl + link);
//            url=new URL("http://rc.majlis.ir/fa/parliament_member/show/861887");
            Document doc = Jsoup.parse(url, 15000);
            final String html = doc.html();
            String temp = html.replace("<br>", "$$$"); //$$$ instead <br>
            temp = temp.replace("<br/>", "$$$"); //$$$ instead <br>
            temp = temp.replace("تسلط بر زبان (های) : ", "تسلط بر زبان (های) : $$$");
            temp = temp.replace("سوابق", "$$$ سوابق");
            temp = temp.replace("بیوگرافی", "$$$ بیوگرافی");
            temp = temp.replace("وبسایت", "$$$ وبسایت");
            doc = Jsoup.parse(temp);

            Member member = new Member();
            {   // member name and image
                member.setMember_id(Long.parseLong(link.substring(link.lastIndexOf("/") + 1)));
                final Elements tabs = doc.select("#myTab > li > a");
                member.setName(tabs.iterator().next().text());

                final Element image = doc.select("#myTabContent > img").first();
                if (image != null) {
                    String imageUrl = image.attr("src");
                    final String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
//                    try (InputStream in = new URL(baseUrl + imageUrl.replace(" ", "%20")).openStream()) {
//                        Files.copy(in, Paths.get(saveImageLocation + fileName), StandardCopyOption.REPLACE_EXISTING);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    member.setImageFileName(fileName);
                }
            }
            {   // member info
                String personalInfo = doc.select("#myTabContent").iterator().next().text();
                String state = "";
                for (String s : personalInfo.split("\\$\\$\\$")) {
                    s = persianNormalize(s.trim());
                    if (s.startsWith("سال تولد")) {
                        final String yearString = s.substring(s.lastIndexOf(' ') + 1);
                        member.setBirthYear(Integer.parseInt(yearString));
                    } else if (s.startsWith("محل تولد")) {
                        s = s.split(":")[1];
                        member.setBirthPlace(s.trim());
                    } else if (s.contains("زبان")) {
                        state = "lang";
                    } else if (s.contains("سوابق"))
                        state = "resume";
                    else if (s.contains("تحصیلات"))
                        state = "study";
                    else if (s.contains("بیوگرافی"))
                        state = "bio";
                    else if (s.contains("وبسایت"))
                        state = "web";
                    else if (!s.isEmpty()) {
                        if (Objects.equals(state, "lang")) {
                            for (String lang : s.split("،")) {
                                member.getLanguages().add(lang);
                            }
                        }
                        if (state.equals("resume")) {
                            if (s.startsWith("-"))
                                s = s.substring(1).trim();
                            member.getResume().add(s);
                        }
                        if (state.equals("study")) {
                            if (s.startsWith("-"))
                                s = s.substring(1).trim();
                            member.getStudy().add(s);
                        }
                        if (state.equals("bio")) {
                            member.setBio(member.getBio() + s + "\n");
                        }
                        if (state.equals("web")) {
                            if (!s.trim().isEmpty())
                                member.setWebsite(s.trim());
                        }
                    }
                }

            }
            // doreh history
            int doreh = 0;
            Elements select = doc.select("#myTabContent > .tab-pane");
            Elements tabs = doc.select("#myTab > li > a");
            for (int i = 1; i < select.size(); i++) {
                Element element = select.get(i);
                DorehHistory d = new DorehHistory();
                try {
                    d.setDoreh(tabs.get(i).text());
                    String hozeh = doc.select(String.format("#ltab%d > span:nth-child(1)", doreh)).text().split(":")[1].trim();
                    String subHozeh = hozeh.substring(hozeh.indexOf("(") + 1, hozeh.indexOf(")")).trim();
                    hozeh = hozeh.substring(0, hozeh.indexOf("(")).trim();
                    d.setHozeh(hozeh);
                    d.setSubHozeh(subHozeh);
                } catch (Exception e) {
//                        e.printStackTrace();
                }
                try {
                    Integer vote = Integer.parseInt(doc.select(String.format("#ltab%d > span:nth-child(2)", doreh)).text().replace(",", "").split(":")[1].trim());
                    d.setVotes(vote);
                    Double votePercent = Double.parseDouble(doc.select(String.format("#ltab%d > span:nth-child(3)", doreh)).text().split(":")[1].trim());
                    d.setPrecent(votePercent);
                } catch (Exception e) {
//                        e.printStackTrace();
                }
                try {
                    final Elements links = doc.select(String.format("#ltab%d", doreh)).select("a");
                    for (Element linkInPage : links) {
                        final String href = linkInPage.attr("href");
                        if (href.contains("/parliament_commission/"))
                            d.getCommisions().add(linkInPage.text().trim());
                        if (href.contains("/legal_draft/"))
                            d.getLegalDraft().add(linkInPage.text().trim());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(objectMapper.writeValueAsString(d));
                member.getDorehHistories().add(d);
                doreh++;
            }
            res.add(member);
//            break;
        }

//        if(true)
//            return;
        final String s = objectMapper.writeValueAsString(res);
        System.out.println(s);
        PrintStream p = new PrintStream(new File("./output/memberInfo.json"));
        p.println(s);
        p.close();
    }
}
