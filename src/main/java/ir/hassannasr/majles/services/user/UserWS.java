package ir.hassannasr.majles.services.user;

import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.domain.exceptoin.InvalidParameterException;
import ir.hassannasr.majles.domain.user.Endorse;
import ir.hassannasr.majles.domain.user.User;
import ir.hassannasr.majles.domain.user.UserManager;
import ir.hassannasr.majles.services.BaseWS;
import ir.hassannasr.majles.services.response.CandidView;
import ir.hassannasr.majles.services.response.EndorseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by hassan on 20/12/2015.
 */

@Service
@Path("/user")
public class UserWS extends BaseWS {


    @Autowired
    UserManager userManager;
    @Autowired
    CandidManager candidManager;

    @GET
    @Path("/userInfo")
    @Produces("application/json")
    public Response getMyInfo(@QueryParam("id") Long userId) {
        try {
            if (getUserInSite() == null)
                return sendError("NotLoggedIn");
            if (userId.equals(Long.parseLong(getUserInSite()))) {
                User user = null;
                try {
                    user = userManager.get(userId);
                } catch (Exception e) {
                }
                if (user == null)
                    user = userManager.createNewUser(userId);
                return Response.ok(getJsonCreator().getJson(user)).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                sendError("ServerError");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }

    @Path("/addEndorse")
    @GET
    @Produces("application/json")
    public Response addEndorse(@QueryParam("candidId") Long candidId, @QueryParam("context") String context, @QueryParam("credit") Integer credit) {
        try {

            if (getUserInSite() == null) {
                return sendError("NotLoggedIn");
            }
            Long userId = Long.parseLong(getUserInSite());
            User user = userManager.get(userId);

            if (user.getEndorseCredit() < credit) {
                return sendError("lowCredit");
            }
            Candid c = candidManager.get(candidId);
            if (c == null) {
                return sendError("candidNotFount");
            }
            final Endorse endorse = userManager.endorse(user, c, context, credit);
            EndorseResponse response = new EndorseResponse();
            response.setEndorse(endorse);
            candidManager.refresh(c);
//            candidManager.evict(c);
//            c=candidManager.get(c.getId());
            response.setCandid(new CandidView(c));
            response.setUser(user);
            return Response.ok(getJsonCreator().getJson(response)).build();

        } catch (IOException | InvalidParameterException e) {
            e.printStackTrace();
            try {
                return sendError(e.getMessage());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }


    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
