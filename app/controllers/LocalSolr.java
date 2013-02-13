package controllers;

import models.Ad;
import models.Solr;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Timer;
import views.html.ads;
import views.html.facets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocalSolr extends Controller {

    private static Solr solr = new Solr();

    static {
        Timer tim = new Timer();
        solr = new Solr();
        System.out.println("Initialized server in " + tim.stop() + "ms");
    }

    public static Result search() throws SolrServerException {
        Timer tim = new Timer();
        QueryResponse rsp = solr.search("*:*");
        List<Ad> adList = rsp.getBeans(Ad.class);
        System.out.println("Searched all ads in " + tim.stop() + "ms");
        return ok(ads.render(adList, rsp.getResults().getNumFound(), tim.stop()));
    }

    static long getCount() throws SolrServerException {
        Timer tim = new Timer();
        long numFound = solr.count("*:*");
        System.out.println("found size of index in " + tim.stop() + "ms");
        return numFound;
    }

    public static Result facets(int numFacets) throws SolrServerException {
        Timer tim = new Timer();
        QueryResponse rsp = solr.facet("*:*", numFacets, "companyname");
        List<FacetField.Count> companies = rsp.getFacetField("companyname").getValues();
        System.out.println("Got " + companies.size() + " facets in " + tim.stop() + "ms");
        return ok(facets.render(companies, rsp.getResults().getNumFound(), tim.stop()));
    }

    public static Result add(int many) throws IOException, SolrServerException {
        Timer tim = new Timer();
        List<Ad> ads = new ArrayList<>();
        for (int i = 0; i < many; i++) {
            ads.add(Ad.getAd());
        }
        solr.addBeans(ads);

        String debug = "Added " + ads.size() + " ads in " + tim.stop() + "ms";
        System.out.println(debug);
        flash("message", debug);

        return redirect(routes.Application.index());
    }

    public static Result removeAll() throws IOException, SolrServerException {
        Timer tim = new Timer();
        solr.deleteByQuery("*:*");

        String debug = "Cleared index in " + tim.stop() + "ms";
        System.out.println(debug);
        flash("message", debug);

        return redirect(routes.Application.index());
    }
}
