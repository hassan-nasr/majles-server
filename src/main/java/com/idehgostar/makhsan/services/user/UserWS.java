package com.idehgostar.makhsan.services.user;

import com.idehgostar.makhsan.core.encryption.CipherUtils;
import com.idehgostar.makhsan.services.BaseWS;
import com.idehgostar.makhsan.domain.exceptoin.InvalidParameterException;
import com.idehgostar.makhsan.domain.permission.PermissionManager;
import com.idehgostar.makhsan.domain.permission.PermissionManagerImpl;
import com.idehgostar.makhsan.domain.role.Role;
import com.idehgostar.makhsan.domain.role.RoleDao;
import com.idehgostar.makhsan.domain.singleaccesstoken.InvalidTokenException;
import com.idehgostar.makhsan.domain.singleaccesstoken.SingleAccessToken;
import com.idehgostar.makhsan.domain.singleaccesstoken.SingleAccessTokenManager;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticateInfo;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticationFailedException;
import com.idehgostar.makhsan.domain.user.User;
import com.idehgostar.makhsan.domain.user.UserCreationException;
import com.idehgostar.makhsan.domain.user.UserManager;
import com.idehgostar.makhsan.services.response.AskRegister;
import com.idehgostar.makhsan.services.response.UserInfo;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.idehgostar.makhsan.services.response.SimpleResponse.Status;

/**
 * Created by hassan on 02/11/2015.
 * a class for User management and registering
 */

@Service
@Path("/user")
public class UserWS  extends BaseWS{
    private static final int ASK_REGISTER_VALID_MINUTES = 10;
    @Autowired
    UserManager userManager;
    @Autowired
    CipherUtils cipherUtils;
    @Autowired
    SingleAccessTokenManager singleAccessTokenManager;
    @Autowired
    PermissionManager permissionManager;
    @Autowired
    RoleDao roleDao;


    @GET
    @Path("/askRegister")
    public String askForRegister() throws IOException {
        String captcha = "captcha";
        SingleAccessToken singleAccessToken = singleAccessTokenManager.generateAccessToken(null, SingleAccessToken.Type.Register, ASK_REGISTER_VALID_MINUTES, 1, captcha);
        AskRegister askRegister = new AskRegister(singleAccessToken.getToken(),captcha);
        return getJsonCreator().getJson(askRegister);
    }

    @GET
    @Path("/register")
//    TODO:hassan authorize user for role assignment
    public String register(@QueryParam("email") String email,
                           @QueryParam("firstName")String firstName,
                           @QueryParam("lastName")String lastName,
                           @QueryParam("password")String password,
                           @QueryParam("roles")String rolesString,
                           @QueryParam("registerAccessToken")String registerAccessToken,
                           @QueryParam("captchaValue")String captchaValue,
                           @Context HttpServletResponse response) throws IOException {
//        Verifying Captcha only if not already in site
        if(getUserInSite()==null)
            try {
                SingleAccessToken singleAccessToken = singleAccessTokenManager.useToken(registerAccessToken);
                if(!Objects.equals(singleAccessToken.getData(),captchaValue)) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return createSimpleResponse(Status.Failed, "Invalid Captcha Value");
                }
            } catch (InvalidTokenException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return createSimpleResponse(Status.Failed, "Invalid Register Access Token");
            }
//        Check User Can Create Another User
        if(getUserInSite()!=null){
            if(!hasPermission(PermissionManagerImpl.CREATE_USER))
                return createSimpleResponse(Status.Failed, "You Can Not Create Another User");
        }
//        Creating User
        List<Role> roles = null;
        if(hasPermission(PermissionManagerImpl.ASSIGN_ROLE)) {
            roles = extractRoles(rolesString);
        }
        User user;
        try {
            user = userManager.createUser(email, firstName, lastName, password, roles);
        if(user.getId()!=null)
            return createSimpleResponse(Status.Success,"User Created Successfully");
        else
            return createSimpleResponse(Status.Failed, "Error Creating User");
        } catch (UserCreationException | InvalidParameterException e) {
            return createSimpleResponse(Status.Failed, e.getMessage());
        }
    }
// TOD:error on missing role
    protected List<Role> extractRoles(String rolesString) {
        List<Role> roles;
        roles = new ArrayList<>();
        if (rolesString != null)
            for (String s : rolesString.split(",")) {
                final Role e = roleDao.loadByRoleName(s);
                if(e!=null)
                    roles.add(e);
            }
        return roles;
    }


    @GET
    @Path("/authenticate")
    public String authenticateUser(@QueryParam("email")String email,
                                   @QueryParam("service")String serviceName,
                                   @QueryParam("password")String password) throws IOException {
        try {
            AuthenticateInfo authenticateInfo = userManager.authenticateUser(email, password, serviceName);
            return getJsonCreator().getJson(authenticateInfo);
        } catch (AuthenticationFailedException e) {
            return createSimpleResponse(Status.Failed,e.getMessage());
        }

    }


    @GET
    @Path("/info")
    public String userInfo(@QueryParam("user_id") String userId) throws IOException {
        User user = userManager.get(Long.parseLong(userId));
        String loggedInUserId = getUserInSite();
        UserInfo userInfo = new UserInfo(user, !(ObjectUtils.equals(loggedInUserId,userId) || hasPermission(PermissionManagerImpl.ASSIGN_ROLE)));
        return getJsonCreator().getJson(userInfo);

    }


    @GET
    @Path("/manageRoles")
    public String assignRole(@QueryParam("user_id")String userId,
                      @QueryParam("roles") String rolesString,
                      @QueryParam("action") String action) throws IOException {
        if(!hasPermission(PermissionManagerImpl.ASSIGN_ROLE))
            return createSimpleResponse(Status.Failed, "You Can Not Change Roles(Permission Denied)");
        final User user = userManager.get(Long.valueOf(userId));
        final List<Role> roles = extractRoles(rolesString);
        if(user.getId().equals(1l))
            return createSimpleResponse(Status.Failed, "You Can Not Change Admin Roles");
        if("add".equals(action)) {
            for (Role role : roles) {
                user.getRoles().add(role);
            }
        }
        else if("remove".equals(action)) {
            for (Role role : roles) {
                user.getRoles().remove(role);
            }
        }else{
            return createSimpleResponse(Status.Failed, "Unknown Action");
        }
        return  createSimpleResponse(Status.Success,"Roles Updated");
    }
}
