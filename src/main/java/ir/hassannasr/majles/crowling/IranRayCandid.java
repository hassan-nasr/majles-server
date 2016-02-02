package ir.hassannasr.majles.crowling;

import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by hassan on 01/02/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class IranRayCandid {
    Long id;
    String imageUrl;
    String fullName;
    Integer party;
    SubHozeh subHozeh;
    String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getParty() {
        return party;
    }

    public void setParty(Integer party) {
        this.party = party;
    }

    public SubHozeh getSubHozeh() {
        return subHozeh;
    }

    public void setSubHozeh(SubHozeh subHozeh) {
        this.subHozeh = subHozeh;
    }
}
