package ir.hassannasr.majles.crowling;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hassan on 09/12/2015.
 */
public class DorehHistory {
    String hozeh;
    Integer votes;
    Double precent;
    Set<String> commisions = new HashSet<>();
    Set<String> legalDraft = new HashSet<>();
    private String subHozeh;
    private String doreh;

    public Set<String> getCommisions() {
        return commisions;
    }

    public void setCommisions(Set<String> commisions) {
        this.commisions = commisions;
    }

    public String getHozeh() {
        return hozeh;
    }

    public void setHozeh(String hozeh) {
        this.hozeh = hozeh;
    }

    public Set<String> getLegalDraft() {
        return legalDraft;
    }

    public void setLegalDraft(Set<String> legalDraft) {
        this.legalDraft = legalDraft;
    }

    public Double getPrecent() {
        return precent;
    }

    public void setPrecent(Double precent) {
        this.precent = precent;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public String getSubHozeh() {
        return subHozeh;
    }

    public void setSubHozeh(String subHozeh) {
        this.subHozeh = subHozeh;
    }

    public String getDoreh() {
        return doreh;
    }

    public void setDoreh(String doreh) {
        this.doreh = doreh;
    }
}
