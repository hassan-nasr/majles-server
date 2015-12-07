package ir.hassannasr.majles.core.solr;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * Created by hassan on 11/10/2015.
 */
public interface SolrFacade {
    /**
     * first creates a string query by concating elements with 'AND' operator and then calls {@link #search(String, int, int, HighlightOptions, String, int, java.util.Set)}
     *
     * @param queryElements
     * @param start
     * @param count
     * @param highlightOptions
     * @param idField          which it groups on this field also
     * @param groupSizeLimit
     * @param otherFields
     * @return
     * @throws Exception
     */
    QueryResult search(Collection<QueryElement> queryElements, int start, int count, HighlightOptions highlightOptions, String idField, int groupSizeLimit, Set<String> otherFields) throws Exception;


    QueryResult search(String queryString, int firstIndex, int maxResults, HighlightOptions highlightOptions, String groupField, int groupSizeLimit, Set<String> otherFields) throws Exception;

    Long count(String queryString, String groupFiled) throws Exception;

    void setAutoCommit(boolean autoCommit);

    void commit() throws IOException, SolrServerException;
}
