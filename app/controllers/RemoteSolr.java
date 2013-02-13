package controllers;

import models.Ad;
import models.Solr;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Timer;
import views.html.ads;

import java.util.List;

/**
 * User: frerodla
 * Date: 12.02.13
 * Time: 10:36
 */
public class RemoteSolr extends Controller {

    private static Solr solr = new Solr("solr1.finntech.no", 12100);

    public static Result search() throws SolrServerException {
        Timer tim = new Timer();

        QueryResponse rsp = solr.geoQuery();

        List<Ad> adList = rsp.getBeans(Ad.class);
        System.out.println("Got " + adList.size() + " ads from " + solr + " in " + tim.stop() + "ms");
        return ok(ads.render(adList, rsp.getResults().getNumFound(), tim.stop()));
    }


}
