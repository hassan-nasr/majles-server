package com.idehgostar.makhsan.core.solr;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: Hasan
 * Date: 2/28/13
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class SolrServerFactory {
    /**
     * create a solr Client on on a given server address.
     *
     * @param address
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public HttpSolrClient getHttpSolrServer(String address) throws IOException, SolrServerException {
        HttpSolrClient ret = new HttpSolrClient(address);
//        ret.ping();
        return ret;
    }

    /**
     * not working yet
     * @param solrHome
     * @param collectionName
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    public EmbeddedSolrServer getEmbeddedSolrServer(String solrHome,String collectionName) throws IOException, SolrServerException {
        EmbeddedSolrServer ret = null;
        /*System.setProperty("solr.solr.home", solrHome);
        CoreContainer.Initializer initializer = new CoreContainer.Initializer();
        CoreContainer coreContainer = initializer.initialize();
        ret = new EmbeddedSolrServer(coreContainer, collectionName);
//        ret.ping();*/
        return ret;
    }
}
