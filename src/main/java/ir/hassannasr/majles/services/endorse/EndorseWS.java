package ir.hassannasr.majles.services.endorse;

import ir.hassannasr.majles.services.BaseWS;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by hassan on 21/12/2015.
 */
@Service
@Path("/endorse")
public class EndorseWS extends BaseWS {

    @GET
    @Path("/endorseNames")
    @Produces("application/json")
    public String getEndorseNames() {
        try {
            final Properties properties = new Properties();
            properties.load(EndorseWS.class.getResourceAsStream("endorseNames.properties"));
            Map<String, String> ret = new HashMap<>();
            for (Map.Entry<Object, Object> e : properties.entrySet()) {
                ret.put((String) e.getKey(), (String) e.getValue());
            }
            return getJsonCreator().getJson(ret);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                return getJsonCreator().getJson(new HashMap<>());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return "";
        }
    }
}
