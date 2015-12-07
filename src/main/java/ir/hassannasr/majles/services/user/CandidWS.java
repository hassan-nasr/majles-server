package ir.hassannasr.majles.services.user;

import com.idehgostar.makhsan.core.encryption.CipherUtils;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.services.BaseWS;
import ir.hassannasr.majles.services.response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.io.IOException;

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


    @GET
    @Path("/publicInfo")
    public String userInfo(@QueryParam("candidId") String candiId) throws IOException {
        if (getUserInSite() == null)
            return getJsonCreator().getJson(new SimpleResponse(SimpleResponse.Status.Failed, "notLoggedIn"));
        Candid candid = candidManager.get(Long.parseLong(candiId));
        return getJsonCreator().getJson(candid);
    }


}
