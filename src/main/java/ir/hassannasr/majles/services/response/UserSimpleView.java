package ir.hassannasr.majles.services.response;

import ir.hassannasr.majles.domain.user.User;

/**
 * Created by hassan on 30/12/2015.
 */
public class UserSimpleView {
    Long id;
    String phone;
    String imageId;
    Long subHozehId;
    String name;

    public UserSimpleView(User user) {
        this.id = user.getId();
        this.phone = user.getPhone();
        imageId = user.getImageId();
        subHozehId = user.getSubHozeh().getId();
        name = user.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSubHozehId() {
        return subHozehId;
    }

    public void setSubHozehId(Long subHozehId) {
        this.subHozehId = subHozehId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }
}