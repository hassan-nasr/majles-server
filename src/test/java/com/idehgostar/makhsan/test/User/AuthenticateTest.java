package com.idehgostar.makhsan.test.User;

import JerseyTest.AbstractSpringAwareJerseyTest;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticateInfo;
import com.idehgostar.makhsan.services.response.AskRegister;
import com.idehgostar.makhsan.services.response.SimpleResponse;
import com.idehgostar.makhsan.services.response.UserInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * Created by hassan on 08/11/2015.
 */
public class AuthenticateTest extends AbstractSpringAwareJerseyTest {
        @Test
    public void authenticate() throws IOException {
        String askRegisterResponse = resource().path("/user/askRegister").get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        AskRegister askRegister = mapper.readValue(askRegisterResponse, AskRegister.class);
        // REGISTER USER
        String email = "hne1991@live.com";
        final String predictedUserId = "2";
        String registerUserResponse = resource().path("/user/register")
                .queryParam("email", email)
                .queryParam("firstName", "hassan")
                .queryParam("lastName", "nasr")
                .queryParam("password", "password")
                .queryParam("registerAccessToken", askRegister.getRegister_access_token())
                .queryParam("captchaValue", askRegister.getCaptcha_image_url())
                .get(String.class);

        SimpleResponse simpleResponse = mapper.readValue(registerUserResponse, SimpleResponse.class);
        Assert.assertEquals(simpleResponse.getStatus(), SimpleResponse.Status.Success);

        // Authenticate User
        String authenticateResponse = resource().path("/user/authenticate")
                .queryParam("email", email)
                .queryParam("password", "password").get(String.class);

        AuthenticateInfo authenticateInfo = mapper.readValue(authenticateResponse, AuthenticateInfo.class);
        Assert.assertEquals(authenticateInfo.getEmail(), email);
        Assert.assertTrue(authenticateInfo.getExpireDate() > new Date().getTime());
        Assert.assertEquals(authenticateInfo.getUserId(), predictedUserId);
    }

    @Test
    public void authenticateAndGetInfo() throws IOException {
        String askRegisterResponse = resource().path("/user/askRegister").get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        AskRegister askRegister = mapper.readValue(askRegisterResponse, AskRegister.class);
        // REGISTER USER
        String email = "hne1991@live.com";
        String registerUserResponse = resource().path("/user/register")
                .queryParam("email", email)
                .queryParam("firstName", "hassan")
                .queryParam("lastName", "nasr")
                .queryParam("password", "password")
                .queryParam("registerAccessToken", askRegister.getRegister_access_token())
                .queryParam("captchaValue", askRegister.getCaptcha_image_url())
                .get(String.class);

        SimpleResponse simpleResponse = mapper.readValue(registerUserResponse, SimpleResponse.class);
        Assert.assertEquals(simpleResponse.getStatus(), SimpleResponse.Status.Success);

        // Authenticate User
        String authenticateResponse = resource().path("/user/authenticate")
                .queryParam("email", email)
                .queryParam("password", "password").get(String.class);

        AuthenticateInfo authenticateInfo = mapper.readValue(authenticateResponse, AuthenticateInfo.class);
        Assert.assertEquals(authenticateInfo.getEmail(), email);
        Assert.assertTrue(authenticateInfo.getExpireDate() > new Date().getTime());

        final String predictedUserID = "2";
        Assert.assertEquals(authenticateInfo.getUserId(), predictedUserID);

        String myInfo = resource().path("/user/info")
                .queryParam("user_id", predictedUserID)
                .queryParam("access_token", authenticateInfo.getAccessToken())
                .get(String.class);
        UserInfo userInfo = mapper.readValue(myInfo, UserInfo.class);
        Assert.assertEquals(userInfo.getEmail(), email);

        myInfo = resource().path("/user/info")
                .queryParam("user_id", predictedUserID)
                .get(String.class);
        userInfo = mapper.readValue(myInfo, UserInfo.class);
        Assert.assertEquals(userInfo.getEmail(), null);
    }

}
