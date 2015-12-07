package com.idehgostar.makhsan.services.role;

import com.idehgostar.makhsan.core.JsonCreator.JsonCreator;
import com.idehgostar.makhsan.services.BaseWS;
import com.idehgostar.makhsan.domain.role.Role;
import com.idehgostar.makhsan.domain.role.RoleManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */

@Service
@Path("/role")
public class RoleWS extends BaseWS{


    @Autowired
    RoleManager roleManager;

    @GET
    @Path("/all")
    public String getAllRoles() throws IOException {

        List<Role> roleList = roleManager.getAll();
        JsonCreator jsonCreator= getJsonCreator();
        return jsonCreator.getJson(roleList);
    }


    public RoleManager getRoleManager() {
        return roleManager;
    }

    public void setRoleManager(RoleManager roleManager) {
        this.roleManager = roleManager;
    }
}
