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

/**
 * Created by hassan on ?.
 */
/*
@RunWith(PowerMockRunner.class)
@PrepareForTest({SecurityUtils.class})
*/
public class RegisterUserTest extends AbstractSpringAwareJerseyTest {


    private final String admin_email = "admin@makhsan.com";
    private final String makhsan_admin_role = "Makhsan_Admin";
    private String admin_password = "namespace1392";
    private String accessToken;
    private String makhsan_admin_role1;

    @Test
    public void registerSuccess() throws IOException {
        // GET ACCESS TOKEN

        AskRegister askRegister = UserWSTestFunctions.getAskRegister(resource());
        // REGISTER USER
        String firstName = "hassan";
        String lastName = "nasr";
        final String predictedUserId = "2";

        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1991@live.com", "namespace", firstName, lastName, resource());
        Assert.assertEquals(SimpleResponse.Status.Success, simpleResponse.getStatus());


        final UserInfo userInfo = UserWSTestFunctions.getUserInfo(predictedUserId, "", resource());
        Assert.assertEquals(firstName, userInfo.getFirstName());
        Assert.assertEquals(lastName, userInfo.getLastName());
        Assert.assertEquals(predictedUserId, userInfo.getUserId());
    }

    @Test
    public void reuseToken() throws IOException {
        // GET ACCESS TOKEN

        AskRegister askRegister = askRegister();
        // REGISTER USER
        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1991@live.com", "namespace", "hassan", "nasr", resource());
        Assert.assertEquals(SimpleResponse.Status.Success, simpleResponse.getStatus());

        // REUSE USED TOKEN

        simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1992@live.com", "namespace", "hassan", "nasr", resource());

        Assert.assertEquals(SimpleResponse.Status.Failed, simpleResponse.getStatus());
        Assert.assertEquals("Invalid Register Access Token", simpleResponse.getMessage());
    }
    @Test
    public void duplicateUser() throws IOException {
        // GET ACCESS TOKEN

        AskRegister askRegister = askRegister();
        // REGISTER USER

        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1991@live.com", "namespace", "hassan", "nasr", resource());
        Assert.assertEquals(SimpleResponse.Status.Success, simpleResponse.getStatus());


        // GET ANOTHER TOKEN
        askRegister = askRegister();

        // Register with same Email

        simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1991@live.com", "namespace", "hassan", "nasr", resource());

        Assert.assertEquals(SimpleResponse.Status.Failed, simpleResponse.getStatus());
        Assert.assertEquals("User Exists", simpleResponse.getMessage());
    }
    @Test
    public void incompleteRegister_InvalidEmail() throws IOException {
        // GET ACCESS TOKEN

        AskRegister askRegister = askRegister();
        // REGISTER USER
        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1991live.com", "namespace", "hassan", "nasr", resource());
        Assert.assertEquals(SimpleResponse.Status.Failed, simpleResponse.getStatus());
    }
    @Test
    public void incompleteRegister_InvalidPassword() throws IOException {
        // GET ACCESS TOKEN

        AskRegister askRegister = askRegister();
        // REGISTER USER

        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1991@live.com", "", "hassan", "nasr", resource());
        Assert.assertEquals(SimpleResponse.Status.Failed, simpleResponse.getStatus());
    }
    @Test
    public void incompleteRegister_InvalidName() throws IOException {
        // GET ACCESS TOKEN
        AskRegister askRegister = askRegister();
        // REGISTER USER

        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), "hne1991@live.com", "password", "hassan", "", resource());
        Assert.assertEquals(SimpleResponse.Status.Failed, simpleResponse.getStatus());
    }
    @Test
    public void incompleteRegister_NoToken() throws IOException {
        // GET ACCESS TOKEN

        AskRegister askRegister = UserWSTestFunctions.getAskRegister(resource());
        // REGISTER USER

        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister("", askRegister.getCaptcha_image_url(), "hne1991@live.com", "namespace", "hassan", "nasr", resource());
        Assert.assertEquals(SimpleResponse.Status.Failed, simpleResponse.getStatus());
    }
    @Test
    public void incompleteRegister_WrongCaptcha() throws IOException {
        // GET ACCESS TOKEN
        AskRegister askRegister = askRegister();
        // REGISTER USER
        SimpleResponse simpleResponse = UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), "wrong captcha", "hne1991@live.com", "namespace", "hassan", "nasr", resource());
        Assert.assertEquals(SimpleResponse.Status.Failed, simpleResponse.getStatus());
    }

    protected AskRegister askRegister() throws IOException {
        String askRegisterResponse = resource().path("/user/askRegister").get(String.class);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(askRegisterResponse, AskRegister.class);
    }

    @Test
    public void registerAnotherUserByAdmin() throws IOException {
        // Authenticate Admin

        AuthenticateInfo authenticateInfo = UserWSTestFunctions.getAuthenticateInfo(admin_email, admin_password, resource());
        String firstName = "hassan";
        String lastName = "nasr";
        final String predictedUserId = "2";

//        Register User with Adming
        SimpleResponse simpleResponse = UserWSTestFunctions.registerUser(authenticateInfo.getAccessToken(), firstName, lastName, "hne1991@live.com", "password", makhsan_admin_role, resource());
        Assert.assertEquals(SimpleResponse.Status.Success, simpleResponse.getStatus());
        UserInfo userInfo = UserWSTestFunctions.getUserInfo(predictedUserId, "", resource());
        Assert.assertEquals(firstName, userInfo.getFirstName());
        Assert.assertEquals(lastName, userInfo.getLastName());
        Assert.assertEquals(predictedUserId, userInfo.getUserId());

    }

    @Test
    public void registerAnotherUserByAdminAndGrantAdminRole() throws IOException {
        // Authenticate Admin
        AuthenticateInfo authenticateInfo = UserWSTestFunctions.getAuthenticateInfo(admin_email, admin_password, resource());
        // create user
        String firstName = "hassan";
        String lastName = "nasr";
        final String predictedUserId = "2";
        final String userEmail = "hne1991@live.com";
        final String userPassword = "password";
        SimpleResponse simpleResponse = UserWSTestFunctions.registerUser(authenticateInfo.getAccessToken(), firstName, lastName, userEmail, userPassword, makhsan_admin_role, resource());
        Assert.assertEquals(SimpleResponse.Status.Success, simpleResponse.getStatus());

//      get user info
        UserInfo userInfo = UserWSTestFunctions.getUserInfo(predictedUserId, authenticateInfo.getAccessToken(), resource());
        Assert.assertEquals(firstName, userInfo.getFirstName());
        Assert.assertEquals(lastName, userInfo.getLastName());
        Assert.assertEquals(predictedUserId, userInfo.getUserId());
        Assert.assertTrue(userInfo.getRoles().contains(makhsan_admin_role));
//      authenticate user

        authenticateInfo = UserWSTestFunctions.getAuthenticateInfo(userEmail, userPassword, resource());
//        create another user with new admin

        // create user
        final String firstName2 = "ali";
        final String lastName2 = "alavi";
        final String predictedUserId2 = "3";
        final String userEmail2 = "ali@makhsan.com";
        final String userPassword2 = "password";
        simpleResponse = UserWSTestFunctions.registerUser(authenticateInfo.getAccessToken(), firstName2, lastName2, userEmail2, userPassword2, "", resource());


        Assert.assertEquals(SimpleResponse.Status.Success, simpleResponse.getStatus());

//      get user info
        userInfo = UserWSTestFunctions.getUserInfo(predictedUserId2, authenticateInfo.getAccessToken(), resource());
        Assert.assertEquals(firstName2, userInfo.getFirstName());
        Assert.assertEquals(lastName2, userInfo.getLastName());
        Assert.assertEquals(predictedUserId2, userInfo.getUserId());
    }

}
