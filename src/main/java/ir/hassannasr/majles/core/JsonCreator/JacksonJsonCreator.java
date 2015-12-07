package ir.hassannasr.majles.core.JsonCreator;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.IOException;

/**
 * Created by arsalan on 10/10/15.
 */
public class JacksonJsonCreator implements JsonCreator {

    @Override
    public String getJson(Object object) throws IOException {

        ObjectMapper objMapper=new ObjectMapper();
        objMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
        return objMapper.writeValueAsString(object);

    }
}
