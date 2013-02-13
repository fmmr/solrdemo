package controllers;

import models.Ad;
import models.Solr;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Timing;
import views.html.ads;

import java.util.List;

/**
 * User: frerodla
 * Date: 12.02.13
 * Time: 10:36
 */
@Timing
public class RemoteSolr extends Controller {

    private static Solr solr = new Solr("solr1.finntech.no", 12100);

    public static Result search(String pos) throws SolrServerException {
        QueryResponse rsp = solr.geoQuery(pos);
        List<Ad> adList = rsp.getBeans(Ad.class);
        return ok(ads.render(adList, rsp.getResults().getNumFound()));
    }
}
