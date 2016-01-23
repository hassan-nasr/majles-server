package ir.hassannasr.majles.services.response;

import ir.hassannasr.majles.domain.candid.Candid;

import java.util.List;

/**
 * Created by hassan on 07/12/2015.
 */
public class CandidSimpleView {
    Long id;
    String name;
    String hozeh;
    String subHozeh;
    String imageId;
    Long userId;
    String code;
    Long content;

    public CandidSimpleView() {
    }

    public CandidSimpleView(Long id, String hozeh, String subHozeh, String imageId, List<String> name, String code, Long userId) {
        this.hozeh = hozeh;
        this.id = id;
        this.imageId = imageId;
        this.name = name.get(0);
        this.subHozeh = subHozeh;
        this.code = code;
        this.userId = userId;
    }

    public CandidSimpleView(Candid candid) {
        this.hozeh = candid.getHozeh();
        this.id = candid.getId();
        this.imageId = candid.getImageId();
        this.name = candid.getName();
        this.subHozeh = candid.getSubHozeh();
        userId = candid.getUserId();
        code = candid.getCode();
    }

    public Long getContent() {
        return content;
    }

    public void setContent(Long content) {
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHozeh() {
        return hozeh;
    }

    public void setHozeh(String howzeh) {
        this.hozeh = howzeh;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubHozeh() {
        return subHozeh;
    }

    public void setSubHozeh(String subHozeh) {
        this.subHozeh = subHozeh;
    }
}
