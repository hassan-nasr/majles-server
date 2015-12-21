package ir.hassannasr.majles.services.candid;

import com.idehgostar.makhsan.core.encryption.CipherUtils;
import ir.hassannasr.majles.core.solr.*;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.services.BaseWS;
import ir.hassannasr.majles.services.response.CandidSearchResult;
import ir.hassannasr.majles.services.response.CandidSimpleView;
import ir.hassannasr.majles.services.response.CandidView;
import ir.hassannasr.majles.services.response.SimpleResponse;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

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

    @GET
    @Path("/publicInfo")
    @Produces("application/json")
    public String userInfo(@QueryParam("candidId") String candiId) throws IOException {
        if (getUserInSite() == null)
            return getJsonCreator().getJson(new SimpleResponse(SimpleResponse.Status.Failed, "notLoggedIn"));
        Candid candid = candidManager.get(Long.parseLong(candiId));
        CandidView candidView = new CandidView(candid);
        if (candid.getSupporterIds() == null || candid.getSupporterIds().isEmpty()) {
            for (int i = 0; i < 10; i++) {
                candid.getSupporterIds().add(Math.abs(random.nextLong()) % 290 + 1);
            }
        }

        for (Long id : candid.getSupporterIds()) {
            final Candid candid1 = candidManager.get(id);
            candidView.getSupporters().add(new CandidSimpleView(candid1));
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
                for (int i = 0; i < 10; i++) {
                    candid.getSupporterIds().add(Math.abs(random.nextLong()) % 290 + 1);
                }
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
    @Path("/search")
    @Produces("application/json")
    public Response search(@QueryParam("query") String query,
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
            QueryElement queryElement = new StringQueryElement("all", query);
            final ArrayList<QueryElement> queryElements = new ArrayList<>();
            queryElements.add(queryElement);
            final HashSet<String> otherFields = new HashSet<>();
            otherFields.add("name");
            otherFields.add("hozeh");
            otherFields.add("subhozeh");
            otherFields.add("imageId");
            final QueryResult<GroupResultElement> result = new SolrFacadeImpl(solrClient).search(queryElements, from, count, new HighlightOptions(), "id", 1, otherFields);
            CandidSearchResult ret = new CandidSearchResult();
            ret.setCount(result.getCount());
            for (GroupResultElement groupResultElement : result.getResultList()) {
                final SolrDocument solrDoc = ((ResultElement) groupResultElement.getResultElements().get(0)).getSolrDoc();
                ret.getResult().add(new CandidSimpleView((Long) solrDoc.get("id"), (String) solrDoc.get("hozeh"), (String) solrDoc.get("subhozeh"), (String) solrDoc.get("imageId"), (List<String>) solrDoc.get("name")));
            }
            return Response.ok(getJsonCreator().getJson(ret)).build();

        } catch (Exception e) {
            e.printStackTrace();
            return sendError("searchError");
        }
    }

}
