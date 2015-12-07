package com.idehgostar.makhsan.test.User;

import JerseyTest.AbstractSpringAwareJerseyTest;
import com.idehgostar.makhsan.domain.user.Auth.AuthenticateInfo;
import com.idehgostar.makhsan.services.response.AskRegister;
import com.idehgostar.makhsan.services.response.SimpleResponse;
import com.idehgostar.makhsan.services.response.UserInfo;
import com.idehgostar.makhsan.test.Vars;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by hassan on 10/11/2015.
 */
public class RoleManageTest extends AbstractSpringAwareJerseyTest {

    @Test
    public void assignRole() throws IOException {
        final AskRegister askRegister = UserWSTestFunctions.getAskRegister(resource());
        UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), Vars.userEmail, Vars.userPassword, Vars.firstName, Vars.lastName, resource());
        final AuthenticateInfo authenticateInfo = UserWSTestFunctions.getAuthenticateInfo(Vars.adminEmail, Vars.adminPassword, resource());
        final SimpleResponse addRoleResponse = UserWSTestFunctions.manageRole(authenticateInfo.getAccessToken(), Vars.predictedCreatedUserId, Vars.adminRole, "add", resource());
        Assert.assertEquals(SimpleResponse.Status.Success, addRoleResponse.getStatus());

        final UserInfo userInfo = UserWSTestFunctions.getUserInfo(Vars.predictedCreatedUserId, authenticateInfo.getAccessToken(), resource());
        Assert.assertTrue(userInfo.getRoles().contains(Vars.adminRole));
    }

    @Test
    public void removeAdminsRoleDenied() throws IOException {
        final AskRegister askRegister = UserWSTestFunctions.getAskRegister(resource());
        UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), Vars.userEmail, Vars.userPassword, Vars.firstName, Vars.lastName, resource());
        final AuthenticateInfo authenticateInfo = UserWSTestFunctions.getAuthenticateInfo(Vars.adminEmail, Vars.adminPassword, resource());
        final SimpleResponse addRoleResponse = UserWSTestFunctions.manageRole(authenticateInfo.getAccessToken(), Vars.adminId, Vars.adminRole, "remove", resource());
        Assert.assertEquals(SimpleResponse.Status.Failed, addRoleResponse.getStatus());

        final UserInfo userInfo = UserWSTestFunctions.getUserInfo(Vars.adminId, authenticateInfo.getAccessToken(), resource());
        Assert.assertTrue(userInfo.getRoles().contains(Vars.adminRole));
    }

    @Test
    public void addRoleDenied() throws IOException {
        final AskRegister askRegister = UserWSTestFunctions.getAskRegister(resource());
        UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), Vars.userEmail, Vars.userPassword, Vars.firstName, Vars.lastName, resource());
        final AuthenticateInfo authenticateInfo = UserWSTestFunctions.getAuthenticateInfo(Vars.userEmail, Vars.userPassword, resource());
        final SimpleResponse addRoleResponse = UserWSTestFunctions.manageRole(authenticateInfo.getAccessToken(), Vars.predictedCreatedUserId, Vars.adminRole, "add", resource());
        Assert.assertEquals(SimpleResponse.Status.Failed, addRoleResponse.getStatus());
        final UserInfo userInfo = UserWSTestFunctions.getUserInfo(Vars.predictedCreatedUserId, authenticateInfo.getAccessToken(), resource());
        Assert.assertTrue(!userInfo.getRoles().contains(Vars.adminRole));
    }

    @Test
    public void removeRole() throws IOException {
        final AskRegister askRegister = UserWSTestFunctions.getAskRegister(resource());
        UserWSTestFunctions.selfRegister(askRegister.getRegister_access_token(), askRegister.getCaptcha_image_url(), Vars.userEmail, Vars.userPassword, Vars.firstName, Vars.lastName, resource());
        final AuthenticateInfo authenticateInfo = UserWSTestFunctions.getAuthenticateInfo(Vars.adminEmail, Vars.adminPassword, resource());
        final SimpleResponse addRoleResponse = UserWSTestFunctions.manageRole(authenticateInfo.getAccessToken(), Vars.predictedCreatedUserId, Vars.adminRole, "add", resource());
        Assert.assertEquals(SimpleResponse.Status.Success, addRoleResponse.getStatus());

        UserInfo userInfo = UserWSTestFunctions.getUserInfo(Vars.predictedCreatedUserId, authenticateInfo.getAccessToken(), resource());
        Assert.assertTrue(userInfo.getRoles().contains(Vars.adminRole));

        final SimpleResponse removeRoleResponse = UserWSTestFunctions.manageRole(authenticateInfo.getAccessToken(), Vars.predictedCreatedUserId, Vars.adminRole, "remove", resource());
        Assert.assertEquals(SimpleResponse.Status.Success, removeRoleResponse.getStatus());

        userInfo = UserWSTestFunctions.getUserInfo(Vars.predictedCreatedUserId, authenticateInfo.getAccessToken(), resource());
        Assert.assertTrue(!userInfo.getRoles().contains(Vars.adminRole));
    }
}
