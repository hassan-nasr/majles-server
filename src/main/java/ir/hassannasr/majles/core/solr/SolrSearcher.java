package ir.hassannasr.majles.core.solr;

import core.base.QuerySourcesUtil;

/**
 * Created by hassan on 19/10/2015.
 */
public abstract class SolrSearcher {
    /**
     * returns all results for the search
     *
     * @return
     */
    public abstract QueryResult<GroupResultElement> computeDefaultList();

    /**
     * returns result of the search with given boundaries
     * @param firstIndex first result index: 0 base
     * @param maxResults number of results
     * @return list of GroupResultElement
     */
    public abstract QueryResult<GroupResultElement> computeDefaultList(int firstIndex, int maxResults);

    /**
     * returns list of IDs for the search with given boundaries
     *
     * @param firstIndex first result index: 0 base
     * @param maxResults number of results
     * @return list of Ids
     */
    public abstract QueryResult<Long> computeDefaultIds(QuerySourcesUtil qsu, int firstIndex, int maxResults);

    /**
     * sets a specific id so only returns that document
     * @param rowKey id of the document in string format
     */
    public abstract void setDefaultId(String rowKey);

    /**
     * removes what was done with {@link SolrSearcher#setDefaultId(String) }
     */
    public abstract void resetDefaultId();
}
