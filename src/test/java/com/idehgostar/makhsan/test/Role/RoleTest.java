package com.idehgostar.makhsan.test.Role;

import JerseyTest.AbstractSpringAwareJerseyTest;
import com.idehgostar.makhsan.domain.role.Role;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by hassan on 09/11/2015.
 */
public class RoleTest extends AbstractSpringAwareJerseyTest {
    @Test
    public void getAllRoles() throws IOException {
        // getAllRoles
        String response = resource().path("/role/all").get(String.class);
        ObjectMapper mapper = new ObjectMapper();

        List<Role> simpleResponse = mapper.readValue(response, new TypeReference<List<Role>>() {
        });
        Assert.assertEquals(simpleResponse.get(0).getRoleName(), "Makhsan_Admin");
    }
}
