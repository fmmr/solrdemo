package controllers;

import org.apache.solr.client.solrj.SolrServerException;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * User: frerodla
 * Date: 12.02.13
 * Time: 11:54
 */
public class Application extends Controller {
    public static Result index() throws SolrServerException {
        return ok(index.render(LocalSolr.getCount(), flash()));
    }
}
