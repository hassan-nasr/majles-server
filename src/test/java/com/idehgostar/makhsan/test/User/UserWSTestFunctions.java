package com.idehgostar.makhsan.test.User;

import com.idehgostar.makhsan.domain.user.Auth.AuthenticateInfo;
import com.idehgostar.makhsan.services.response.AskRegister;
import com.idehgostar.makhsan.services.response.SimpleResponse;
import com.idehgostar.makhsan.services.response.UserInfo;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by hassan on 08/11/2015.
 */
public class UserWSTestFunctions {
    public static AuthenticateInfo getAuthenticateInfo(String userEmail, String userPassword, WebResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String authenticateResponse;
        AuthenticateInfo authenticateInfo;
        authenticateResponse = resource.path("/user/authenticate")
                .queryParam("email", userEmail)
                .queryParam("password", userPassword).get(String.class);

        authenticateInfo = mapper.readValue(authenticateResponse, AuthenticateInfo.class);
        return authenticateInfo;
    }

    public static UserInfo getUserInfo(String userId, String accessToken, WebResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String infoResponse;
        UserInfo userInfo;
        infoResponse = resource.path("user/info")
                .queryParam("user_id", userId)
                .queryParam("access_token", accessToken)
                .get(String.class);
        userInfo = mapper.readValue(infoResponse, UserInfo.class);
        return userInfo;
    }

    public static SimpleResponse registerUser(String accessToken, String firstName, String lastName, String userEmail, String userPassword, String makhsan_admin_role, WebResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String registerUserResponse = resource.path("/user/register")
                .queryParam("email", userEmail)
                .queryParam("firstName", firstName)
                .queryParam("lastName", lastName)
                .queryParam("password", userPassword)
                .queryParam("roles", makhsan_admin_role)
                .queryParam("access_token", accessToken)
                .get(String.class);

        return mapper.readValue(registerUserResponse, SimpleResponse.class);
    }

    public static AskRegister getAskRegister(WebResource resource) throws IOException {
        String askRegisterResponse = resource.path("/user/askRegister").get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(askRegisterResponse, AskRegister.class);
    }

    public static SimpleResponse selfRegister(String register_access_token, String captcha, String email, String password, String firstname, String lastName, WebResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String registerUserResponse = resource.path("/user/register")
                .queryParam("email", email)
                .queryParam("firstName", firstname)
                .queryParam("lastName", lastName)
                .queryParam("password", password)
                .queryParam("registerAccessToken", register_access_token)
                .queryParam("captchaValue", captcha)
                .get(String.class);

        return mapper.readValue(registerUserResponse, SimpleResponse.class);
    }

    public static SimpleResponse manageRole(String accessToken, String userId, String roles, String action, WebResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String response = resource.path("/user/manageRoles")
                .queryParam("access_token", accessToken)
                .queryParam("user_id", userId.toString())
                .queryParam("roles", roles)
                .queryParam("action", action)
                .get(String.class);

        return mapper.readValue(response, SimpleResponse.class);
    }
}
