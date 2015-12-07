package com.idehgostar.makhsan.core.solr;

/**
 * Created with IntelliJ IDEA.
 * User: Hasan
 * Date: 4/15/13
 * Time: 5:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class QueryElement {
    private String tag,text;

    public QueryElement(String tag, String text) {
        this.tag = tag;
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    /**
     * sets field tag == solr field name
     *
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getText() {
        return text;
    }

    /**
     * sets field value related to tag. which can be any solr supported value. from text to periods
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return tag+":"+text;
    }
}
