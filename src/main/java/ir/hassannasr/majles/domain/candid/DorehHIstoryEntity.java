package ir.hassannasr.majles.domain.candid;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

/**
 * Created by hassan on 16/12/2015.
 */
@Embeddable
public class DorehHistoryEntity {
    Integer voteCount;
    String hozeh, subHozeh;
    Double votePercent;
    String commisions;
    String legalDrafts;
    String doreh;

    @Lob
    public String getCommisions() {
        return commisions;
    }

    public void setCommisions(String commisions) {
        this.commisions = commisions;
    }

    public String getHozeh() {
        return hozeh;
    }

    public void setHozeh(String hozeh) {
        this.hozeh = hozeh;
    }

    @Lob
    public String getLegalDrafts() {
        return legalDrafts;
    }

    public void setLegalDrafts(String legalDrafts) {
        this.legalDrafts = legalDrafts;
    }

    public String getSubHozeh() {
        return subHozeh;
    }

    public void setSubHozeh(String subHozeh) {
        this.subHozeh = subHozeh;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Double getVotePercent() {
        return votePercent;
    }

    public void setVotePercent(Double votePercent) {
        this.votePercent = votePercent;
    }

    public String getDoreh() {
        return doreh;
    }

    public void setDoreh(String doreh) {
        this.doreh = doreh;
    }
}
