package ir.hassannasr.majles.core.solr;

import org.apache.solr.client.solrj.SolrQuery;

/**
 * Created by hassan on 26/10/2015.
 */
public class HighlightOptions {
    private boolean enableHighlight = true;
    private String startTag = "<h>";
    private String endTag = "</h>";
    private String alternateFiled = "text";
    private int maxAlternateFiledLength = 100;
    private Integer snippetSize = 2;
    private String[] highlightFields = {"text"};

    /**
     * leave each parameter null for default value
     */
    public HighlightOptions() {
        this(true, new String[]{"text"}, null, null, null, null, null);
    }

    /**
     * leave each parameter null for default value
     *
     * @param enableHighlight         cant be null.
     * @param highlightFields         fields to highlight on.
     * @param startTag                default '<h>'
     * @param endTag                  default '</h>'
     * @param alternateFiled          default 'text'
     * @param maxAlternateFiledLength default 100
     * @param snippetSize             default 2
     */
    public HighlightOptions(Boolean enableHighlight, String[] highlightFields, String startTag, String endTag, String alternateFiled, Integer maxAlternateFiledLength, Integer snippetSize) {
        this.enableHighlight = enableHighlight;
        if (highlightFields != null)
            this.highlightFields = highlightFields;
        if (startTag != null)
            this.startTag = startTag;
        if (endTag != null)
            this.endTag = endTag;
        if (alternateFiled != null)
            this.alternateFiled = alternateFiled;
        if (maxAlternateFiledLength != null)
            this.maxAlternateFiledLength = maxAlternateFiledLength;
        if (snippetSize != null)
            this.snippetSize = snippetSize;
    }

    /**
     * inject options to solr Query
     *
     * @param query
     */
    public void setInQuery(SolrQuery query) {
        if (enableHighlight)
            query.setHighlight(true);
        else
            return;
        query.set("hl.simple.pre", startTag);
        query.set("hl.simple.post", endTag);
        query.set("hl.snippets", snippetSize);
        query.set("hl.mergeContiguous", true);
        query.set("hl.alternateField", alternateFiled);
        query.set("hl.maxAlternateFieldLength", maxAlternateFiledLength);
        query.set("hl.requireFieldMatch", true);
        StringBuilder highlightFieldStrings = new StringBuilder();
        for (String highlightField : highlightFields) {
            highlightFieldStrings.append(highlightField).append(",");
        }

        String highlightFieldsConcat = highlightFieldStrings.toString();
        query.setParam("hl.fl", highlightFieldsConcat);
    }

    public boolean isEnableHighlight() {
        return enableHighlight;
    }

    /**
     * enables highlighting feature
     *
     * @param enableHighlight
     */
    public void setEnableHighlight(boolean enableHighlight) {
        this.enableHighlight = enableHighlight;
    }

    public String getStartTag() {
        return startTag;
    }

    /**
     * highlighting start tag
     *
     * @param startTag
     */
    public void setStartTag(String startTag) {
        this.startTag = startTag;
    }

    public String getEndTag() {
        return endTag;
    }

    /**
     * highlighting end tag
     *
     * @param endTag
     */
    public void setEndTag(String endTag) {
        this.endTag = endTag;
    }

    public String getAlternateFiled() {
        return alternateFiled;
    }

    /**
     * if no highlighted part found use this filed to get a snippet from that
     *
     * @param alternateFiled
     */
    public void setAlternateFiled(String alternateFiled) {
        this.alternateFiled = alternateFiled;
    }

    public int getMaxAlternateFiledLength() {
        return maxAlternateFiledLength;
    }

    /**
     * maximum length of the alternate field
     *
     * @param maxAlternateFiledLength
     */
    public void setMaxAlternateFiledLength(int maxAlternateFiledLength) {
        this.maxAlternateFiledLength = maxAlternateFiledLength;
    }

    public Integer getSnippetSize() {
        return snippetSize;
    }

    /**
     * maximums snippet parts (highlighted parts of result)
     *
     * @param snippetSize
     */
    public void setSnippetSize(Integer snippetSize) {
        this.snippetSize = snippetSize;
    }

    public String[] getHighlightFields() {
        return highlightFields;
    }

    /**
     * fields to highlight
     *
     * @param highlightFields
     */
    public void setHighlightFields(String[] highlightFields) {
        this.highlightFields = highlightFields;
    }
}
