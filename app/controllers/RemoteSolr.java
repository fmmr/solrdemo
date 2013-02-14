package controllers;

import models.Ad;
import models.Solr;
import models.SolrHost;
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

    private static Solr solr;

    public static Result search(String pos) throws SolrServerException {
        if (solr == null) {
            return badRequest("No remote solr defined.");
        }
        QueryResponse rsp = solr.geoQuery(pos);
        List<Ad> adList = rsp.getBeans(Ad.class);
        return ok(ads.render(adList, rsp.getResults().getNumFound()));
    }

    public static void setSolr(SolrHost host) {
        solr = new Solr(host);
    }

    public static Solr getSolr() {
        return solr;
    }
}
