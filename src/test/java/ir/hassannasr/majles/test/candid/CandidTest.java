package ir.hassannasr.majles.test.candid;

import JerseyTest.AbstractSpringAwareJerseyTest;
import ir.hassannasr.majles.domain.candid.Candid;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

/**
 * Created by hassan on 09/11/2015.
 */
public class CandidTest extends AbstractSpringAwareJerseyTest {
    @Test
    public void getCandidInfo() throws IOException {


        String accessToken = generateAccessToken("1", new HashSet<>());


        String response = resource().path("/candid/publicInfo")
                .queryParam("access_token", accessToken)
                .queryParam("candidId", "1")
                .get(String.class);
        ObjectMapper mapper = new ObjectMapper();

        Candid candid = mapper.readValue(response, Candid.class);
        Assert.assertEquals((Long) 1L, candid.getId());
    }
}
