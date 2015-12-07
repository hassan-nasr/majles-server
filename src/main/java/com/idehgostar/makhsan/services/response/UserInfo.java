package com.idehgostar.makhsan.services.response;

import com.idehgostar.makhsan.domain.user.User;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hassan on 03/11/2015.
 */
public class UserInfo {
    String userId;
    String firstName;
    String lastName;
    String email;
    List<String> roles;

    public UserInfo() {
    }

    public UserInfo(String userId, String firstName, String lastName, String email, List<String> roles) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public UserInfo(User user, boolean publicOnly) {
        userId=user.getId().toString();
        firstName=user.getFirstnam();
        lastName= user.getLastname();
        if(!publicOnly){
            email = user.getEmail();
            roles = user.getRoles().stream().map((a)->a.getRoleName()).collect(Collectors.toList());
        }
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("roles")
    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
