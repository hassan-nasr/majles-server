package ir.hassannasr.majles.services;

import com.idehgostar.makhsan.core.auth.TokenData;
import core.base.QuerySourcesUtil;
import ir.hassannasr.majles.core.JsonCreator.JacksonJsonCreator;
import ir.hassannasr.majles.services.response.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Set;

/**
 * Created by Abolfazl on 8/15/2015.
 */
public class BaseWS {

   @Autowired
   protected QuerySourcesUtil querySourcesUtil;

   @Context
   private HttpServletRequest request;

   protected JacksonJsonCreator getJsonCreator() {
      return new JacksonJsonCreator();
   }

   protected String createSimpleResponse(SimpleResponse.Status status, String message) throws IOException {
      return getJsonCreator().getJson(new SimpleResponse(status, message));
   }

   protected boolean hasRole(String role) {
      try {
         return ((Set<String>) request.getAttribute("userRoles")).contains(role);
      }catch (Exception e) {
         return false;
      }
   }

   protected boolean hasPermission(String permission) {
      try {
         if (((Set<String>) request.getAttribute("userPermissions")).contains(permission))
            return true;
      }catch(Exception e){
      }
      return false;
   }
   protected String getUserInSite(){
      try {
         return ((String) request.getAttribute("userId"));
      }catch (Exception e) {
         return null;
      }
   }

   protected TokenData getTokenData() {
      try {
         return ((TokenData) request.getAttribute("token"));
      } catch (Exception e) {
         return null;
      }
   }

    protected Response sendError(String message) throws IOException {
        return Response.status(403).entity(new SimpleResponse(SimpleResponse.Status.Failed, message)).build();
    }

   protected Response sendSuccess(String message) throws IOException {
      return Response.ok(new SimpleResponse(SimpleResponse.Status.Success, message)).build();
   }
}
