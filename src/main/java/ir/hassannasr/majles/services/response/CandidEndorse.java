package ir.hassannasr.majles.services.response;

/**
 * Created by hassan on 21/12/2015.
 */
public class CandidEndorse {
    String context;
    Long count;

    public CandidEndorse(String context, Long count) {
        this.context = context;
        this.count = count;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
