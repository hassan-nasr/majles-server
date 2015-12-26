package ir.hassannasr.majles.domain.user;

import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.hozeh.SubHozeh;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hassan on 02/11/2015.
 */


@Entity
public class User extends BaseObject {
    private Long id;
    private Date creationDate;
    private Long endorseCredit;
    private SubHozeh subHozeh;

    private List<Endorse> endorseList = new ArrayList<>();

    public User() {
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
}
