package ir.hassannasr.majles.services.candid;

import com.idehgostar.makhsan.core.encryption.CipherUtils;
import ir.hassannasr.majles.core.solr.*;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.services.BaseWS;
import ir.hassannasr.majles.services.response.CandidSearchResult;
import ir.hassannasr.majles.services.response.CandidSimpleView;
import ir.hassannasr.majles.services.response.SimpleResponse;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 * a class for User management and registering
 */

@Service
@Path("/candid")
public class CandidWS extends BaseWS {
    @Autowired
    CipherUtils cipherUtils;

    @Autowired
    CandidManager candidManager;

    @Autowired
    SolrClient solrClient;


    @GET
    @Path("/publicInfo")
    public String userInfo(@QueryParam("candidId") String candiId) throws IOException {
        if (getUserInSite() == null)
            return getJsonCreator().getJson(new SimpleResponse(SimpleResponse.Status.Failed, "notLoggedIn"));
        Candid candid = candidManager.get(Long.parseLong(candiId));
        return getJsonCreator().getJson(candid);
    }


    @GET
    @Path("/search")
    public String search(@QueryParam("query") String query,
                         @QueryParam("from") Integer from,
                         @QueryParam("count") Integer count) throws IOException {
        if (getUserInSite() == null)
            return getJsonCreator().getJson(new SimpleResponse(SimpleResponse.Status.Failed, "notLoggedIn"));
        try {
            QueryElement queryElement = new StringQueryElement("all", query);
            final ArrayList<QueryElement> queryElements = new ArrayList<>();
            queryElements.add(queryElement);
            final HashSet<String> otherFields = new HashSet<>();
            otherFields.add("name");
            otherFields.add("hozeh");
            otherFields.add("imageId");
            final QueryResult<GroupResultElement> result = new SolrFacadeImpl(solrClient).search(queryElements, from, count, new HighlightOptions(), "id", 1, otherFields);
            CandidSearchResult ret = new CandidSearchResult();
            ret.setCount(result.getCount());
            for (GroupResultElement groupResultElement : result.getResultList()) {
                final SolrDocument solrDoc = ((ResultElement) groupResultElement.getResultElements().get(0)).getSolrDoc();
                ret.getResult().add(new CandidSimpleView((Long) solrDoc.get("id"), (String) solrDoc.get("hozeh"), (String) solrDoc.get("imageId"), (List<String>) solrDoc.get("name")));
            }
            return getJsonCreator().getJson(ret);

        } catch (Exception e) {
            e.printStackTrace();
            return getJsonCreator().getJson(new SimpleResponse(SimpleResponse.Status.Failed, "searchError"));
        }
    }

}
