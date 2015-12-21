package ir.hassannasr.majles.services.response;

import ir.hassannasr.majles.domain.user.Endorse;
import ir.hassannasr.majles.domain.user.User;

/**
 * Created by hassan on 21/12/2015.
 */
public class EndorseResponse {
    Endorse endorse;
    User user;
    CandidView candid;

    public CandidView getCandid() {
        return candid;
    }

    public void setCandid(CandidView candid) {
        this.candid = candid;
    }

    public Endorse getEndorse() {
        return endorse;
    }

    public void setEndorse(Endorse endorse) {
        this.endorse = endorse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
