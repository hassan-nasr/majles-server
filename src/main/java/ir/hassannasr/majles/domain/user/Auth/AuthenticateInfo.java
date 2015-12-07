package ir.hassannasr.majles.domain.user.Auth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */
public class AuthenticateInfo {
    private String accessToken;
    private Long expireDate;
    private String email;
    private String userId;
    private List<String> roles = new ArrayList<>();

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
