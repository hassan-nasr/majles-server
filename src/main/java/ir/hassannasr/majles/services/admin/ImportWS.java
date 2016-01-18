package ir.hassannasr.majles.services.admin;

import ir.hassannasr.majles.crowling.DorehHistory;
import ir.hassannasr.majles.crowling.Member;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.domain.candid.DorehHistoryEntity;
import ir.hassannasr.majles.services.BaseWS;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hassan on 15/12/2015.
 */
@Service
@Path("/admin")
public class ImportWS extends BaseWS {

    public static String saveImageLocation = "D:\\Projects\\Majles\\server\\images";
    public static String baseUrl = "http://www.majlesekhobregan.ir";
    @Autowired
    CandidManager candidManager;

    public static void main(String[] args) throws IOException {
        new ImportWS().importKhebrehMember();
    }

    @Path("/importMember")
    public String importMember() throws IOException {
        InputStream memberInfo = new FileInputStream("D:\\Projects\\Majles\\server\\output\\memberInfoAll.json");
        ObjectMapper mapper = new ObjectMapper();
        final List<Member> members = mapper.readValue(memberInfo, new TypeReference<List<Member>>() {
        });
        for (int i = 290; i < members.size(); i++) {
            Member member = members.get(i);
            Candid candid = new Candid();
            if (member.getName().startsWith("آقای") || member.getName().startsWith("خانم"))
                member.setName(member.getName().substring(4).trim());
            candid.setName(member.getName());
            candid.setImageId(member.getImageFileName());
            if (member.getDorehHistories().size() > 0) {
                candid.setHozeh(member.getDorehHistories().get(0).getHozeh());
                candid.setSubHozeh(member.getDorehHistories().get(0).getSubHozeh());
            }
            if (member.getBirthYear() != null)
                candid.setAge(1395 - member.getBirthYear());
            candid.setBornLocation(member.getBirthPlace());
            candid.setDorehInMajles(member.getDorehHistories().size());
            StringBuilder study = new StringBuilder();
            for (String s : member.getStudy()) {
                study.append(s).append("\n");
            }
            StringBuilder resume = new StringBuilder();
            for (String s : member.getResume()) {
                resume.append(s).append("\n");
            }
            candid.setStudyField(study.toString());
            candid.setResume(resume.toString());
            candid.setBio(member.getBio());
            candid.setWebsite(member.getWebsite());
            candid.setLanguages(member.getLanguages().stream().collect(Collectors.joining(",")));

            for (DorehHistory dorehHistory : member.getDorehHistories()) {
                DorehHistoryEntity dhe = new DorehHistoryEntity();
                dhe.setVoteCount(dorehHistory.getVotes());
                dhe.setVotePercent(dorehHistory.getPrecent());
                dhe.setHozeh(dorehHistory.getHozeh());
                dhe.setSubHozeh(dorehHistory.getSubHozeh());
                dhe.setDoreh(dorehHistory.getDoreh());
                dhe.setCommisions(dorehHistory.getCommisions().stream().collect(Collectors.joining("\n")));
                dhe.setLegalDrafts(dorehHistory.getLegalDraft().stream().collect(Collectors.joining("\n")));
                candid.getDorehHistoryEntities().add(dhe);
            }
            System.err.println(candid.getName());
            candidManager.save(candid);
        }
        return null;
    }

    @Path("/importKhobreh")
    @Produces("application/json")
    @GET
    public Response importKhebrehMember() throws IOException {
        PrintStream out = new PrintStream(new FileOutputStream("./khobrehLink.txt", true));
        List<Candid> candids = new ArrayList<>();
        List<String> images = new ArrayList<>();
        for (int i = 1; i <= 4; i++)
            for (int p = 1; p <= 5; p++) {
//            URL url = new URL(String.format("http://rc.majlis.ir/fa/parliament_member?page=%d&lu_period_no=33", i));
                URL url;
                if (i != 4)
                    url = new URL(String.format("http://www.majlesekhobregan.ir/fa/OldMajlesMemberList.html?CategoryItemID=%d&PN_168=%d", 1016 + i, p));
                else
                    url = new URL(String.format("http://www.majlesekhobregan.ir/fa/MajlesMemberList.html?CategoryItemID=1006&PN_168=%d", p));
                Document doc = Jsoup.parse(url, 20000);

                Elements resultLinks = doc.select("a");
                for (Element link : resultLinks) {
                    String href = link.attr("href");
                    if (href.contains("MajlesMemberView.html")) {
//                        out.println(i + " " + href);
//                        System.out.println(href+ " : " + link.text() );
                        {
                            URL murl = new URL(baseUrl + href);
                            Document mdoc = Jsoup.parse(murl, 15000);
                            String name = null;
                            String hozeh = null;

                            String bio = mdoc.select(".ArticleContentText").iterator().next().text();

                            String birthDate = null;
                            String birthLoc = null;
                            String nameAndHozeh = mdoc.select(".ArticleContentTitle").iterator().next().text();
                            if (href.contains("Old")) {
                                int endIndex = nameAndHozeh.lastIndexOf('(');
                                if (endIndex == -1)
                                    endIndex = nameAndHozeh.length();
                                name = nameAndHozeh.substring(0, endIndex).trim();
                                if (endIndex + 1 < nameAndHozeh.length() - 1)
                                    hozeh = nameAndHozeh.substring(endIndex + 1, nameAndHozeh.length() - 1);
                                else
                                    hozeh = "";
                            } else {
                                name = nameAndHozeh;

                                try {
                                    hozeh = mdoc.select("#ProvinceId_0").iterator().next().text();
                                    birthDate = mdoc.select("#BirthDate_0").iterator().next().text();
                                    birthLoc = mdoc.select("#BirthPlace_0").iterator().next().text();

                                } catch (Exception e) {
                                }
                            }
                            Candid candid = new Candid();
                            candid.setName(name);
                            candid.setSubHozeh(hozeh);
                            candid.setHozeh(hozeh);
                            candid.setBio(bio);
                            candid.setBornLocation(birthLoc);
                            candid.setMajles(false);
                            candid.setCandid(false);
                            if (birthDate != null)
                                candid.setAge(Integer.parseInt(birthDate));
                            candid.setDorehHistoryEntities(new ArrayList<>());
                            final DorehHistoryEntity e = new DorehHistoryEntity();
                            e.setDoreh(new String[]{"دوره اول", "دوره دوم", "دوره سوم", "دوره چهارم"}[i - 1]);
                            e.setHozeh(hozeh);
                            e.setSubHozeh(hozeh);
                            candid.getDorehHistoryEntities().add(e);

                            {
                                final Element image = mdoc.select("img[src*=\"Member\"]").first();
                                if (image != null) {
                                    String imageUrl = image.attr("src");
                                    final String fileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                                    try {
                                        URL in = new URL(baseUrl + imageUrl.replace(" ", "%20"));
                                        System.out.println(in.toString());
                                        images.add(in.toString());
//                                        Files.copy(in, Paths.get(saveImageLocation + fileName), StandardCopyOption.REPLACE_EXISTING);
                                    } catch (Exception e2) {
                                        e2.printStackTrace();
                                    }
                                    candid.setImageId(fileName);
                                }
                            }

                            candids.add(candid);
//                            candidManager.save(candid);
                        }
                    }
                }
            }
        return Response.ok(images).build();
    }
}
