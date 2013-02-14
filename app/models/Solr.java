package models;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.core.CoreContainer;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static org.apache.solr.client.solrj.SolrQuery.ORDER.asc;

/**
 * User: frerodla
 * Date: 12.02.13
 * Time: 15:58
 */
public class Solr {
    private final SolrServer server;
    private final String serverString;

    public Solr() {
        server = getLocalServer();
        serverString = "LOCAL";
    }

    public Solr(SolrHost solrHost) {
        server = getRemoteServer(solrHost + "/solr");
        serverString = solrHost.toString();
    }

    private HttpSolrServer getRemoteServer(String baseURL) {
        return new HttpSolrServer(baseURL);
    }

    public QueryResponse geoQuery(String pos) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery("{!func}geodist()");
        query.setFields("id,companyname,heading,coordinates,score");
        query.setParam("pt", pos);
        query.setParam("d", "10");
        query.setParam("sfield", "coordinates");
        query.setSortField("geodist()", asc);
        query.setRequestHandler("dismax");
        return server.query(query);
    }

    public QueryResponse facet(String query1, int numFacets1, String... facetName) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(query1);
        query.addFacetField(facetName);
        query.setFacetLimit(numFacets1);
        query.setRows(0);
        return server.query(query);
    }

    public QueryResponse search(String query1) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(query1);
        query.setSortField("random_" + UUID.randomUUID().toString(), asc);
        return server.query(query);
    }

    public long count(String query1) throws SolrServerException {
        SolrQuery query = new SolrQuery();
        query.setQuery(query1);
        query.setRows(0);
        QueryResponse rsp = server.query(query);
        return rsp.getResults().getNumFound();
    }

    private SolrServer getLocalServer() {
        System.setProperty("solr.solr.home", "solr");
        CoreContainer.Initializer initializer = new CoreContainer.Initializer();
        CoreContainer coreContainer = null;
        try {
            coreContainer = initializer.initialize();
            System.out.println(coreContainer.getCoreNames());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EmbeddedSolrServer(coreContainer, "collection1");
    }


    @Override
    public String toString() {
        return serverString;
    }

    public void addBeans(List<Ad> ads) throws IOException, SolrServerException {
        server.addBeans(ads);
        server.commit();
    }

    public void deleteByQuery(String s) throws IOException, SolrServerException {
        server.deleteByQuery(s);
        server.commit();
    }


}
