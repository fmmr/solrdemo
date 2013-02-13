package controllers;

import models.Ad;
import models.Solr;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Timer;
import utils.Timing;
import views.html.ads;
import views.html.facets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Timing
public class LocalSolr extends Controller {

    private static Solr solr = new Solr();

    static {
        Timer tim = new Timer();
        solr = new Solr();
        System.out.println("Initialized server in " + tim.stop() + "ms");
    }

    public static Result search() throws SolrServerException {
        QueryResponse rsp = solr.search("*:*");
        List<Ad> adList = rsp.getBeans(Ad.class);
        return ok(ads.render(adList, rsp.getResults().getNumFound()));
    }

    static long getCount() throws SolrServerException {
        return solr.count("*:*");
    }

    public static Result facets(int numFacets) throws SolrServerException {
        QueryResponse rsp = solr.facet("*:*", numFacets, "companyname");
        List<FacetField.Count> companies = rsp.getFacetField("companyname").getValues();
        return ok(facets.render(companies, rsp.getResults().getNumFound()));
    }

    public static Result add(int many) throws IOException, SolrServerException {
        List<Ad> ads = new ArrayList<>();
        for (int i = 0; i < many; i++) {
            ads.add(Ad.getAd());
        }
        solr.addBeans(ads);

        flash("message", "Added " + ads.size() + " ads.");

        return redirect(routes.Application.index());
    }

    public static Result removeAll() throws IOException, SolrServerException {
        solr.deleteByQuery("*:*");
        flash("message", "Cleared index.");
        return redirect(routes.Application.index());
    }
}
