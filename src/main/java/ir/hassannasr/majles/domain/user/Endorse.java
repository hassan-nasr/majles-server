package ir.hassannasr.majles.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ir.hassannasr.majles.domain.base.BaseObject;
import ir.hassannasr.majles.domain.candid.Candid;

import javax.persistence.*;

/**
 * Created by hassan on 20/12/2015.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "loadEndorseByUserCandidContext", query = "from Endorse e where e.candidId=:candidId and e.endorseContext=:endorseContext and e.user.id=:userId")
})
public class Endorse extends BaseObject {
    Long id;
    Long candidId;
    String endorseContext;
    Integer credit;
    @JsonIgnore
    @org.codehaus.jackson.annotate.JsonIgnore
    User user;

    public Endorse(User user, Candid c, String context, Integer credit) {
        this.user = user;

        this.candidId = c.getId();
        this.endorseContext = context;
        this.credit = credit;
    }

    public Endorse() {
    }

    @ManyToOne
    @JsonIgnore
    @org.codehaus.jackson.annotate.JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCandidId() {
        return candidId;
    }

    public void setCandidId(Long candidId) {
        this.candidId = candidId;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getEndorseContext() {
        return endorseContext;
    }

    public void setEndorseContext(String endorseContext) {
        this.endorseContext = endorseContext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endorse endorse = (Endorse) o;

        return !(id != null ? !id.equals(endorse.id) : endorse.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Endorse{" +
                "candidId=" + candidId +
                ", id=" + id +
                ", endorseContext='" + endorseContext + '\'' +
                ", credit=" + credit +
                '}';
    }
}
