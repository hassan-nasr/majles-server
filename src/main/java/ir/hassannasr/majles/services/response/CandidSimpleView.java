package ir.hassannasr.majles.services.response;

import java.util.List;

/**
 * Created by hassan on 07/12/2015.
 */
public class CandidSimpleView {
    Long id;
    String name;
    String hozeh;
    String imageId;

    public CandidSimpleView() {
    }

    public CandidSimpleView(Long id, String hozeh, String imageId, List<String> name) {
        this.hozeh = hozeh;
        this.id = id;
        this.imageId = imageId;
        this.name = name.get(0) + " " + name.get(1);
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
}
