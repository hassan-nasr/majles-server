package com.idehgostar.makhsan.core.solr;

import org.apache.solr.common.SolrDocument;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Hasan
 * Date: 4/15/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ResultElement {

    @JsonIgnore
    private final SolrDocument solrDocument;
    private Long id;
    private Double score;
    @JsonIgnore
    private String highlighted;

    @JsonIgnore
    private Map<String, List<String>> brief;

    private String snippet;

    public ResultElement(Long id, Double score, SolrDocument solrDocument) {
        this.id = id;
        this.score = score;
        this.solrDocument = solrDocument;
    }

    /**
     * document id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * solr score to this item (higher is better).
     * @return
     */
    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getHighlighted() {
        return highlighted;
    }

    public void setHighlighted(String highlighted) {
        this.highlighted = highlighted;
    }

    /**
     * set of brief hilghlited results for each highlight field
     *
     */
    public Map<String, List<String>> getBrief() {
        return brief;
    }

    public void setBrief(Map<String, List<String>> brief) {
        this.brief = brief;
    }

    /**
     * combines the brief results to create a simple snippet
     * @return
     */
    public String getSnippet() {
        if (snippet == null) {
            StringBuilder ret = new StringBuilder();
            StringBuilder retNoTag = new StringBuilder();
            if (brief != null) {

                List<String> values = new ArrayList<>();
                for (List<String> strings : brief.values()) {
                    values.addAll(strings);
                }
                values.sort(new Comparator<String>() {
                    @Override
                    public int compare(String a, String b) {
                        return Integer.compare(b.length(), a.length());
                    }
                });
                for (String s : values) {
                    String sNoTag = s.replaceAll("<[^>]*>", "");
                    if (retNoTag.toString().contains(sNoTag))
                        continue;
                    ret.append("...").append(s);
                    retNoTag.append("...").append(sNoTag);
                }
            }
            ret.append("...");
            snippet = ret.toString();
        }
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     * related solr document for more detailed info about this element.
     * @return
     */
    @JsonIgnore
    public SolrDocument getSolrDoc() {
        return solrDocument;
    }
}
