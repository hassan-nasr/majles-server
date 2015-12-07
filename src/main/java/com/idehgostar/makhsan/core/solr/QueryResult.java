package com.idehgostar.makhsan.core.solr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hassan on 19/10/2015.
 */
public class QueryResult<T> {
    List<T> resultList;
    Integer count;
    Set<String> highlightSet;

    public QueryResult(List<T> resultList, Integer count) {
        this.resultList = resultList;
        this.count = count;
    }

    public QueryResult() {
        resultList = new ArrayList<>();
        count = 0;
        highlightSet = new HashSet<>();
    }

    /**
     * @return list of results
     */
    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    /**
     * number of all results whether retried or not retried
     * @return
     */
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * set of strings which where used to highlight the snippets
     * @return
     */
    public Set<String> getHighlightSet() {
        return highlightSet;
    }

    public void setHighlightSet(Set<String> highlightSet) {
        this.highlightSet = highlightSet;
    }
}
