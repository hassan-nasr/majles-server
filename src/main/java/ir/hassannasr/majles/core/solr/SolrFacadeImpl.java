package ir.hassannasr.majles.core.solr;


import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Hasan
 * Date: 2/21/13
 * Time: 6:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class SolrFacadeImpl implements SolrFacade {

    protected SolrClient solrClient;
    //    private String textField = "text";
    private String idField="id";
    private int maxQuerySize =7000;
//    private int similarityMaxResult = 100;



    private boolean autoCommit = false;

    public SolrFacadeImpl(SolrClient solrServer1) {
        solrClient = solrServer1;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }

    @Override
    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }



    public int getMaxQuerySize() {
        return maxQuerySize;
    }

    public void setMaxQuerySize(int maxQuerySize) {
        this.maxQuerySize = maxQuerySize;
    }

//    public int getSimilarityMaxResult() {
//        return similarityMaxResult;
//    }
//
//    public void setSimilarityMaxResult(int similarityMaxResult) {
//        this.similarityMaxResult = similarityMaxResult;
//    }

    /**
     * first creates a string query by concating elements with 'AND' operator and then calls {@link #search(String, int, int, HighlightOptions, String, int, java.util.Set)}
     *
     * @param queryElements
     * @param start
     * @param count
     * @param highlightOptions
     * @param groupField
     * @param groupSizeLimit
     * @param otherFields
     * @return
     * @throws Exception
     */
    @Override
    public QueryResult search(Collection<QueryElement> queryElements, int start, int count, HighlightOptions highlightOptions, String groupField, int groupSizeLimit, Set<String> otherFields) throws Exception {

        StringBuilder queryString=new StringBuilder();
        boolean first = true;
        for(QueryElement element: queryElements){
            if (!first)
                queryString.append(" AND ");
            else
                first = false;
            queryString.append(element.toString());
        }
        if(queryElements.size()== 0){
            queryString.append("*:*");
        }
        return search(queryString.toString(), start, count, highlightOptions, groupField, groupSizeLimit, otherFields);
    }

    @Deprecated
    public GroupResultElement searchSingle(String queryString, int firstIndex, int maxResults,String[] highlightFields ,Set<String> highlightSet,String idField) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString.toString());
        query.addField("score").addField(idField);
        query.setStart(firstIndex);
        query.setRows(maxResults);
        query.set("hl.simple.pre","<h>");
        query.set("hl.simple.post","</h>");

        query.setHighlight(true); //set Other params as needed
        StringBuilder highlightFieldStrings= new StringBuilder();
        for (String highlightField : highlightFields) {
            highlightFieldStrings.append(highlightField).append(",");
        }
        String highlightFieldsConcat = highlightFieldStrings.toString();
        query.setParam("hl.fl", highlightFieldsConcat);

        QueryResponse response=null;
        try{
            response = solrClient.query(query);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        GroupResultElement  ret = new GroupResultElement();
        SolrDocumentList items = response.getResults();
        for(SolrDocument relatedDoc:items){
            Long id = (Long) relatedDoc.getFieldValue(idField);
            Double score = (double) (Float) relatedDoc.getFieldValue("score");


            ResultElement element = new ResultElement(id, score, relatedDoc);
            Map<String, List<String>> highlightedMap = response.getHighlighting().get(id.toString());
            for (String highlightField : highlightFields) {
                List<String> h = highlightedMap.get(highlightField);
                if(h != null) {
                    for (String s : h) {
                        highlightSet.addAll(getHighlightedWords(s));
                    }
                }
            }
            element.setBrief(highlightedMap);
            ret.add(element);

        }
        return ret;
    }

    /**
     * @param queryString the queryString in solr format more info at http://wiki.apache.org/solr/SolrQuerySyntax
     *                    and http://lucene.apache.org/core/4_0_0/queryparser/org/apache/lucene/queryparser/classic/package-summary.html
     * @param firstIndex  start index
     * @param maxResults  number of results
     * @param highlightOptions fields to highlight matched query words which should be the same as your query search field
     * @param groupField  field to group On useful when you want to group similar hadithes it's should be one of id_S90 or ...
     * @param groupSizeLimit The number of results (documents) to return for each group. Defaults to 1. more info at https://wiki.apache.org/solr/FieldCollapsing
     * @param otherFields
     */
    @Override
    public QueryResult search(String queryString, int firstIndex, int maxResults, HighlightOptions highlightOptions, String groupField, int groupSizeLimit, Set<String> otherFields) throws Exception {
        //idField="id"; //TODO: just for test
        System.out.println("queryString = " + queryString);
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString.toString());
        query.addField("score").addField(idField);
        for (String otherField : otherFields) {
            query.addField(otherField);
        }
        query.set("group", "true");
        query.set("group.ngroups", "true");
        query.set("group.field", groupField);
        query.set("group.limit", groupSizeLimit);
        if (firstIndex >= 0) {
            query.setStart(firstIndex);
            query.setRows(maxResults);
        }

        highlightOptions.setInQuery(query);


        GroupResponse groupResponse = null;
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(query);
            groupResponse = queryResponse.getGroupResponse();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        List<GroupCommand> items = groupResponse.getValues();
        QueryResult ret = new QueryResult();
        if (items.size() == 0)
            return ret;
        for (Group groupResult : items.get(0).getValues()) {
            GroupResultElement resultElementGroup = new GroupResultElement();
            resultElementGroup.setGroupId(Long.parseLong(groupResult.getGroupValue()));
            SolrDocumentList resultList = groupResult.getResult();
            for (SolrDocument result : resultList) {

                Long id = (Long) result.getFieldValue(idField);
                Double score = (double) (Float) result.getFieldValue("score");

                ResultElement element = new ResultElement(id, score, result);
                if (highlightOptions.isEnableHighlight()) {
                    Map<String, List<String>> highlightedMap = queryResponse.getHighlighting().get(id.toString());
                    for (String highlightField : highlightOptions.getHighlightFields()) {
                        List<String> h = highlightedMap.get(highlightField);
                        if (h != null) {
                            for (String s : h) {
                                ret.getHighlightSet().addAll(getHighlightedWords(s));
                            }
                        }
                    }
                    element.setBrief(highlightedMap);
                }
                resultElementGroup.add(element);
            }
            ret.getResultList().add(resultElementGroup);
        }
        ret.setCount(groupResponse.getValues().get(0).getNGroups());
        return ret;
    }

    /**
     * commits change documents
     * @throws IOException
     * @throws SolrServerException
     */
    public void commit() throws IOException, SolrServerException {
        solrClient.commit();
    }

    /**
     * extracs highlited word from a griven string
     * @param s
     * @return
     */
    private Collection<? extends String> getHighlightedWords(String s) {
        s="</h>"+s+"<h>";
        String [] splits =s.split("<h>");
        HashSet<String> ret = new HashSet<String>();
        for(String i:splits){
            int k = i.indexOf("</h>");
            if(k>0) {
                String substring = i.substring(0, k).trim();
                if(substring.contains(" "))
                    continue;
                ret.add(substring);
            }
        }
        return ret;
    }

    @Override
    public Long count(String queryString,String groupField) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(queryString.toString());
        query.setRows(0);
        query.set("group", "true");
        query.set("group.field", groupField);
        query.set("group.ngroups","true");
        QueryResponse response=null;
        try{
            response = solrClient.query(query);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        return (long) response.getGroupResponse().getValues().get(0).getNGroups();
    }

    String normalize(String in){
        StringBuilder sin = new StringBuilder(in);
        for(int i=0;i<in.length();i++){
            if(sin.charAt(i) < 256)
                sin.replace(i,i+1," ");
        }
        return sin.toString();
    }

  /*  boolean highlighting;

    public boolean isHighlighting() {
        return highlighting;
    }

    public void setHighlighting(boolean highlighting) {
        this.highlighting = highlighting;
    }*/
}
