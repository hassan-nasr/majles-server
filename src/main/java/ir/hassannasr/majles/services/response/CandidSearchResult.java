package ir.hassannasr.majles.services.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hassan on 07/12/2015.
 */
public class CandidSearchResult {
    Integer count;
    List<CandidSimpleView> result = new ArrayList<>();

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<CandidSimpleView> getResult() {
        return result;
    }

    public void setResult(List<CandidSimpleView> result) {
        this.result = result;
    }
}
