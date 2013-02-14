package controllers;

import models.SolrHost;
import org.apache.solr.client.solrj.SolrServerException;
import play.Logger;
import play.cache.Cache;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.net.MalformedURLException;

import static play.data.Form.form;

/**
 * User: frerodla
 * Date: 12.02.13
 * Time: 11:54
 */
public class Application extends Controller {

    public static SolrHost solr;

    public static Result index() throws SolrServerException, MalformedURLException {
        SolrHost host = getSolrHost();
        Form<SolrHost> solrForm = form(SolrHost.class).fill(host);
        return ok(index.render(LocalSolr.getCount(), solrForm, flash()));
    }

    private static SolrHost getSolrHost() throws MalformedURLException {
        String uuid = getUUID();
        String host = (String) Cache.get(uuid + "_solr.host");
        System.out.println("host = " + host);
        if (host == null) {
            host = new SolrHost(RemoteSolr.getSolr().toString()).toString();
            session(uuid + "_solr.host", host);
        }
        return new SolrHost(host);
    }

    private static String getUUID() {
        String uuid = session("uuid");
        if (uuid == null) {
            uuid = java.util.UUID.randomUUID().toString();
            session("uuid", uuid);
        }
        return uuid;
    }


    public static Result updateSolrHost() {

        Form<SolrHost> userForm = form(SolrHost.class);
        Form<SolrHost> solrHostForm = userForm.bindFromRequest();

        if (solrHostForm.hasErrors()) {
            Logger.error("ERROR IN FORM");
            return badRequest("ERROR IN FORM");
        }

        SolrHost solr = solrHostForm.get();
        if (solr != null) {
            RemoteSolr.setSolr(solr);
            session(getUUID()+ "_solr.host", solr.toString());
            flash("message", "Solr host updated to: " + solr);
        } else {
            Logger.error("NO SOLR FOUND");
            flash("message", "Solr host not found");
        }

        return redirect(routes.Application.index());
    }
}
