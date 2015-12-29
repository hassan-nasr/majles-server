package ir.hassannasr.majles.services.response;

import ir.hassannasr.majles.domain.candid.Candid;
import ir.hassannasr.majles.domain.user.Endorse;

public class EndorseView {
    Long id;
    Long candidId;
    String endorseContext;
    Integer credit;
    String candidName;

    public EndorseView(Endorse e, Candid c) {
        id = e.getId();
        candidId = e.getCandidId();
        endorseContext = e.getEndorseContext();
        credit = e.getCredit();
        candidName = c.getName();
    }

    public EndorseView() {
    }

    public String getCandidName() {
        return candidName;
    }

    public void setCandidName(String candidName) {
        this.candidName = candidName;
    }

    public Long getId() {
        return id;
    }

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

        EndorseView endorse = (EndorseView) o;

        return !(id != null ? !id.equals(endorse.id) : endorse.id != null);

    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "EndorseView{" +
                "candidId=" + candidId +
                ", id=" + id +
                ", endorseContext='" + endorseContext + '\'' +
                ", credit=" + credit +
                '}';
    }
}
