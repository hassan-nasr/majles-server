package ir.hassannasr.majles.core.solr;

import org.apache.solr.util.SolrPluginUtils;

/**
 * Created by hassan on 07/12/2015.
 */
public class StringQueryElement extends QueryElement {
    public StringQueryElement(String tag, String text) {
        super(tag, text);
    }


    @Override
    public String toString() {

        String newText = SolrPluginUtils.partialEscape(text).toString();
        if (newText.contains("\\s+") && !newText.startsWith("\""))
            return tag + ":\"" + newText + "\"";
        return tag + ":" + newText;
    }
}
