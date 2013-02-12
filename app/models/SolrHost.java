package models;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;

/**
 * User: frerodla
 * Date: 12.02.13
 * Time: 15:58
 */
public class SolrHost {
    public String host;
    public int port;


    public SolrHost(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public SolrServer getSolrServer() {
        return new HttpSolrServer("http://" + host + ":" + port + "/solr");
    }
}
