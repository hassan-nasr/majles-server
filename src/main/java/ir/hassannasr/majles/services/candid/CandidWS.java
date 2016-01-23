package ir.hassannasr.majles.services.candid;

import com.idehgostar.makhsan.core.encryption.CipherUtils;
import ir.hassannasr.majles.core.solr.*;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.domain.candid.EndorseCount;
import ir.hassannasr.majles.domain.user.PhoneConnectionDao;
import ir.hassannasr.majles.domain.user.User;
import ir.hassannasr.majles.domain.user.UserManager;
import ir.hassannasr.majles.services.BaseWS;
import ir.hassannasr.majles.services.response.*;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hassan on 02/11/2015.
 * a class for User management and registering
 */

@Service
@Path("/candid")
public class CandidWS extends BaseWS {
    final static Random random = new Random();
    @Autowired
    CipherUtils cipherUtils;
    @Autowired
    CandidManager candidManager;
    @Autowired
    SolrClient solrClient;

    @Autowired
    UserManager userManager;

    @Autowired
    PhoneConnectionDao phoneConnectionDao;

    @GET
    @Path("/publicInfo")
    @Produces("application/json")
    public String userInfo(@QueryParam("candidId") String candiId) throws IOException {
        if (getUserInSite() == null)
            return getJsonCreator().getJson(new SimpleResponse(SimpleResponse.Status.Failed, "notLoggedIn"));
        Candid candid = candidManager.get(Long.parseLong(candiId));
        CandidView candidView = new CandidView(candid);
        if (candid.getSupporterIds() == null || candid.getSupporterIds().isEmpty()) {
//            for (int i = 0; i < 10; i++) {
//                candid.getSupporterIds().add(Math.abs(random.nextLong()) % 290 + 1);
//            }
        }

        for (Long id : candid.getSupporterIds()) {
            final Candid candid1 = candidManager.get(id);
            candidView.getSupporters().add(new CandidSimpleView(candid1));
        }

        final User user = userManager.get(Long.parseLong(getUserInSite()));
        Set<User> friends = phoneConnectionDao.getMyConnections(user);
//        if(friends.size()==0)
        friends.add(user);
        List<User> inLists = userManager.getUsersContainingCandidOrFromUsers(candid, friends);
        for (User inList : inLists) {
//            TODO handel candids(replace null)
            candidView.getSupportedUsers().add(new UserSimpleView(inList, null));
        }
        return getJsonCreator().getJson(candidView);
    }

    @GET
    @Path("/getAll")
    @Produces("application/json")
    public Response getAll() throws IOException {
        if (getUserInSite() == null)
            return sendError("notLoggedIn");
        List<Candid> candids = candidManager.getAll();
        List<CandidView> candidViews = new ArrayList<>();
        for (Candid candid : candids) {
            CandidView candidView = new CandidView(candid);
            if (candid.getSupporterIds() == null || candid.getSupporterIds().isEmpty()) {
//                for (int i = 0; i < 10; i++) {
//                    candid.getSupporterIds().add(Math.abs(random.nextLong()) % 290 + 1);
//                }
            }
            for (Long id : candid.getSupporterIds()) {
                final Candid candid1 = candidManager.get(id);
                candidView.getSupporters().add(new CandidSimpleView(candid1));
            }
            candidViews.add(candidView);
        }

        return Response.ok(getJsonCreator().getJson(candidViews)).build();
    }


    @GET
    @Path("/searchStatistics")
    @Produces("application/json")
    public Response search(@QueryParam("context") String context,
                           @QueryParam("subHozehId") Long subHozehId) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (getUserInSite() == null)
            return sendError("notLoggedIn");
        try {
            List<Candid> result = candidManager.searchStatistic(context, subHozehId);
            final CandidSearchResult ret = new CandidSearchResult();
            ret.setCount(result.size());
            List<CandidSimpleView> resultSV = new ArrayList<>();
            Method m = EndorseCount.class.getMethod("get" + Character.toUpperCase(context.charAt(0)) + context.substring(1));
            for (Candid candid : result) {
                final CandidSimpleView e = new CandidSimpleView(candid);
                e.setContent((Long) m.invoke(candid.getEndorseCount()));
                resultSV.add(e);
            }
            ret.setResult(resultSV);
            return Response.ok(ret).build();
        } catch (Exception e) {
            return sendError("Exception");
        }
    }

    @GET
    @Path("/search")
    @Produces("application/json")
    public Response search(@QueryParam("query") String query,
                           @QueryParam("majles") Boolean isMajles,
                           @QueryParam("from") Integer from,
                           @QueryParam("count") Integer count) throws IOException {
        if (getUserInSite() == null)
            return sendError("notLoggedIn");
        try {
            if (query == null || query.trim().isEmpty())
                query = "";
            if (from == null)
                from = 0;
            if (count == null)
                count = 50;

            final ArrayList<QueryElement> queryElements = new ArrayList<>();
            for (String s : query.split("\\s+")) {
                QueryElement queryElement = new StringQueryElement("all", s);
                queryElements.add(queryElement);
            }
            if (isMajles != null) {
                queryElements.add(new QueryElement("majles", isMajles ? "1" : "0"));
            }
            final HashSet<String> otherFields = new HashSet<>();
            otherFields.add("name");
            otherFields.add("hozeh");
            otherFields.add("subhozeh");
            otherFields.add("imageId");
            otherFields.add("code");
            otherFields.add("userId");
            final QueryResult<GroupResultElement> result = new SolrFacadeImpl(solrClient).search(queryElements, from, count, new HighlightOptions(), "id", 1, otherFields);
            CandidSearchResult ret = new CandidSearchResult();
            ret.setCount(result.getCount());
            for (GroupResultElement groupResultElement : result.getResultList()) {
                final SolrDocument solrDoc = ((ResultElement) groupResultElement.getResultElements().get(0)).getSolrDoc();
                ret.getResult().add(new CandidSimpleView((Long) solrDoc.get("id"), (String) solrDoc.get("hozeh"), (String) solrDoc.get("subhozeh"), (String) solrDoc.get("imageId"), (List<String>) solrDoc.get("name"),(String) solrDoc.get("code"),(Long)solrDoc.get("userId")));
            }
            return Response.ok(getJsonCreator().getJson(ret)).build();

        } catch (Exception e) {
            e.printStackTrace();
            return sendError("searchError");
        }
    }

    @GET
    @Path("/allHozeh")
    @Produces("application/json")
    public String getAllHozeh() {
        try {
            return getJsonCreator().getJson(candidManager.getAllHozeh());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GET
    @Path("/getByDoreh")
    @Produces("application/json")
    public Response getByDoreh(@QueryParam("doreh") Integer doreh,
                               @QueryParam("majles") Boolean isMajles
    ) {
        try {
            if (getUserInSite() == null)
                return sendError("notLoggedIn");

            if (isMajles == null)
                isMajles = true;
            final List<Candid> candids = candidManager.searchByDoreh(doreh, isMajles);
            CandidSearchResult ret = new CandidSearchResult();
            ret.setCount(candids.size());
            ret.setResult(candids.stream().map(CandidSimpleView::new).collect(Collectors.toList()));
            return Response.ok(ret).build();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
