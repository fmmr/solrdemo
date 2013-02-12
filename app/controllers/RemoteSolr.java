package controllers;

import models.Ad;
import utils.Timer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.ads;

import java.util.List;

/**
 * User: frerodla
 * Date: 12.02.13
 * Time: 10:36
 */
public class RemoteSolr extends Controller {

    private static final String HOST = "solr1.finntech.no";
    private static final int PORT = 12100;
    private static HttpSolrServer server = new HttpSolrServer("http://" + HOST + ":" + PORT + "/solr");

    public static Result index() throws SolrServerException {
        Timer tim = new Timer();
        SolrQuery query = new SolrQuery();
        query.setQuery("{!func}geodist()");
        query.setFields("id,companyname,heading,fulladdress,coordinates,score");
        query.setParam("pt", "59.91374,10.74385");
        query.setParam("d", "10");
        query.setParam("sfield", "coordinates");
        query.setSortField("geodist()", SolrQuery.ORDER.asc);
        query.setQueryType("dismax");

        QueryResponse rsp = server.query(query);
        List<Ad> adList = rsp.getBeans(Ad.class);
        return ok(ads.render(adList, tim.stop()));
    }


}
