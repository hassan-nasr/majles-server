package ir.hassannasr.majles.services.response;

import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.candid.CandidManager;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;
import ir.hassannasr.majles.domain.user.Endorse;
import ir.hassannasr.majles.domain.user.User;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */


public class UserView {
    private Long id;
    private Date creationDate;
    private Long endorseCredit;
    private SubHozeh subHozeh;
    private Boolean verified;
    private String name;
    private String imageId;
    private String phone;
    private List<CandidSimpleView> myChoseCandids = new ArrayList<>();
    private List<CandidSimpleView> myFollowingCandids = new ArrayList<>();
    private List<EndorseView> endorseList = new ArrayList<>();
    private Long verifiedCredit;

    public UserView(User user, CandidManager candidManager, boolean showPrivate) {
        id = user.getId();

        if (user.getMyChoseCandids() != null)
            for (Candid candid : user.getMyChoseCandids()) {
                myChoseCandids.add(new CandidSimpleView(candid));
            }
        subHozeh = user.getSubHozeh();
        verified = user.getVerified();
        name = user.getName();
        imageId = user.getImageId();
        phone = user.getPhone();

        if (showPrivate) {
            creationDate = user.getCreationDate();
            endorseCredit = user.getEndorseCredit();
            if (user.getMyFollowingCandids() != null)
                for (Candid candid : user.getMyFollowingCandids()) {
                    myFollowingCandids.add(new CandidSimpleView(candid));
                }
            for (Endorse endorse : user.getEndorseList()) {
                if (endorse.getCredit() > 0)
                    endorseList.add(new EndorseView(endorse, candidManager.getCached(endorse.getCandidId())));
            }
            verifiedCredit = user.getVerifiedCredit();
        }
    }

    public UserView() {
    }

    public Long getVerifiedCredit() {
        return verifiedCredit;
    }

    public void setVerifiedCredit(Long verifiedCredit) {
        this.verifiedCredit = verifiedCredit;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @ManyToOne
    public SubHozeh getSubHozeh() {
        return subHozeh;
    }

    public void setSubHozeh(SubHozeh subHozeh) {
        this.subHozeh = subHozeh;
    }

    public Long getEndorseCredit() {
        return endorseCredit;
    }

    public void setEndorseCredit(Long endorseCredit) {
        this.endorseCredit = endorseCredit;
    }

    @OneToMany(mappedBy = "user")
    public List<EndorseView> getEndorseList() {
        return endorseList;
    }

    public void setEndorseList(List<EndorseView> endorseList) {
        this.endorseList = endorseList;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "creationDate=" + creationDate +
                ", id=" + id +
                '}';
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public List<CandidSimpleView> getMyChoseCandids() {
        return myChoseCandids;
    }

    public void setMyChoseCandids(List<CandidSimpleView> myChoseCandids) {
        this.myChoseCandids = myChoseCandids;
    }

    public List<CandidSimpleView> getMyFollowingCandids() {
        return myFollowingCandids;
    }

    public void setMyFollowingCandids(List<CandidSimpleView> myFollowingCandids) {
        this.myFollowingCandids = myFollowingCandids;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
