package controllers;

import models.ResultSet;
import models.Solr;
import models.SolrHost;
import org.apache.solr.client.solrj.SolrServerException;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Timing;
import views.html.ads;

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

        ResultSet result = solr.geoQuery(pos);
        return ok(ads.render(result));
    }

    public static void setSolr(SolrHost host) {
        solr = new Solr(host);
    }

    public static Solr getSolr() {
        return solr;
    }
}
