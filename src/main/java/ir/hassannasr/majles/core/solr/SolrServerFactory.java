package ir.hassannasr.majles.core.solr;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.ModifiableSolrParams;

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
    public HttpSolrClient getHttpSolrServer(String address, String username, String password) throws IOException, SolrServerException {
        ModifiableSolrParams params = new ModifiableSolrParams();
//        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS, 128);
//        params.set(HttpClientUtil.PROP_MAX_CONNECTIONS_PER_HOST, 32);
//        params.set(HttpClientUtil.PROP_FOLLOW_REDIRECTS, false);
        DefaultHttpClient httpClient = new DefaultHttpClient();
        httpClient.addRequestInterceptor(new PreEmptiveBasicAuthenticator(username, password));
//        httpClient = new InsecureHttpClient(httpClient, username, password);
        HttpSolrClient ret = new HttpSolrClient(address, httpClient);
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

    public class PreEmptiveBasicAuthenticator implements HttpRequestInterceptor {
        private final UsernamePasswordCredentials credentials;

        public PreEmptiveBasicAuthenticator(String user, String pass) {
            credentials = new UsernamePasswordCredentials(user, pass);
        }

        @Override
        public void process(HttpRequest request, HttpContext context)
                throws HttpException, IOException {
            request.addHeader(BasicScheme.authenticate(credentials, "US-ASCII", false));
        }
    }
}
