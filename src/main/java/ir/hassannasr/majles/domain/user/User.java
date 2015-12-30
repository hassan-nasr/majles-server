package ir.hassannasr.majles.domain.user;

import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;

import javax.persistence.*;
import java.util.*;

/**
 * Created by hassan on 02/11/2015.
 */


@Entity
@NamedQueries({
        @NamedQuery(name = "loadUsersWithPhone", query = "from User u where u.phone in :phone")
})
public class User extends BaseObject {
    private Long id;
    private Date creationDate;
    private Long endorseCredit;
    private SubHozeh subHozeh;
    private Boolean verified;
    private String name;
    private String imageId;
    private String phone;
    private Set<Candid> myChoseCandids = new HashSet<>();
    private Set<Candid> myFollowingCandids = new HashSet<>();
    private List<Endorse> endorseList = new ArrayList<>();

    public User() {
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
    public List<Endorse> getEndorseList() {
        return endorseList;
    }

    public void setEndorseList(List<Endorse> endorseList) {
        this.endorseList = endorseList;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    @Id
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return !(id != null ? !id.equals(user.id) : user.id != null);

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

    @ManyToMany()
    @JoinTable(name = "user_choiceCandid")
    public Set<Candid> getMyChoseCandids() {
        return myChoseCandids;
    }

    public void setMyChoseCandids(Set<Candid> myChoseCandids) {
        this.myChoseCandids = myChoseCandids;
    }

    @ManyToMany()
    @JoinTable(name = "user_followCandid")
    public Set<Candid> getMyFollowingCandids() {
        return myFollowingCandids;
    }

    public void setMyFollowingCandids(Set<Candid> myFollowingCandids) {
        this.myFollowingCandids = myFollowingCandids;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
