package ir.hassannasr.majles.crowling;

/**
 * Created by hassan on 12/01/2016.
 */
public class KhobreganCrowler {


//    public static String baseUrl = "http://www.majlesekhobregan.ir/";
//
//    public static void main(String[] args) throws IOException {
//        PrintStream out = new PrintStream(new FileOutputStream("./khobrehLink.txt", true));
//        List<Candid> candids = new ArrayList<>();
//        for (int i = 1; i <= 4; i++)
//            for (int p = 1; p <= 5; p++) {
////            URL url = new URL(String.format("http://rc.majlis.ir/fa/parliament_member?page=%d&lu_period_no=33", i));
//                URL url;
//                if (i != 4)
//                    url = new URL(String.format("http://www.majlesekhobregan.ir/fa/OldMajlesMemberList.html?CategoryItemID=%d&PN_168=%d", 1016 + i, p));
//                else
//                    url = new URL(String.format("http://www.majlesekhobregan.ir/fa/MajlesMemberList.html?CategoryItemID=1006&PN_168=%d", p));
//                Document doc = Jsoup.parse(url, 2000);
//
//                Elements resultLinks = doc.select("a");
//                for (Element link : resultLinks) {
//                    String href = link.attr("href");
//                    if (href.contains("MajlesMemberView.html")) {
//                        out.println(i + " " + href);
//                        System.out.println(link.text() + " : " + href);
//                        {
//                            URL murl = new URL(baseUrl + href);
////            url=new URL("http://rc.majlis.ir/fa/parliament_member/show/861887");
//                            Document mdoc = Jsoup.parse(murl, 15000);
//                            String name = null;
//                            String hozeh = null;
//
//                            String bio = mdoc.select("#ArticleContentText").iterator().next().text();
//
//                            String birthDate = null;
//                            String birthLoc = null;
//                            String nameAndHozeh = mdoc.select("#ArticleContentTitle").iterator().next().text();
//                            if (href.contains("Old")) {
//                                int endIndex = nameAndHozeh.lastIndexOf('(');
//                                if (endIndex == -1)
//                                    endIndex = nameAndHozeh.length();
//                                name = nameAndHozeh.substring(0, endIndex);
//                                if (endIndex < nameAndHozeh.length() - 1)
//                                    hozeh = nameAndHozeh.substring(endIndex, nameAndHozeh.length() - 1);
//                                else
//                                    hozeh = "";
//                            } else {
//                                name = nameAndHozeh;
//
//                                try {
//                                    hozeh = mdoc.select("#ProvinceId_0").iterator().next().text();
//                                    birthDate = mdoc.select("#BirthDate_0").iterator().next().text();
//                                    birthLoc = mdoc.select("#BirthPlace_0").iterator().next().text();
//
//                                } catch (Exception e) {
//                                }
//                            }
//                            Candid candid = new Candid();
//                            candid.setName(name);
//                            candid.setSubHozeh(hozeh);
//                            candid.setHozeh(hozeh);
//                            candid.setBio(bio);
//                            candid.setBornLocation(birthLoc);
//                            candid.setAge(Integer.parseInt(birthDate));
//                            candid.setDorehHistoryEntities(new ArrayList<>());
//                            final DorehHistoryEntity e = new DorehHistoryEntity();
//                            e.setDoreh(new String[]{"دوره اول", "دوره دوم", "دوره سوم", "دوره چهارم"}[i - 1]);
//                            e.setHozeh(hozeh);
//                            e.setSubHozeh(hozeh);
//                            candid.getDorehHistoryEntities().add(e);
//                            candids.add(candid);
//                        }
//                    }
//                }
//            }
//    }
}
