package controllers;

import models.Ad;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.core.CoreContainer;
import play.mvc.Controller;
import play.mvc.Result;
import utils.Timer;
import views.html.ads;
import views.html.facets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.apache.solr.client.solrj.SolrQuery.ORDER.asc;

public class LocalSolr extends Controller {
    private static SolrServer server;

    static {
        Timer tim = new Timer();
        System.setProperty("solr.solr.home", "solr");
        CoreContainer.Initializer initializer = new CoreContainer.Initializer();
        CoreContainer coreContainer = null;
        try {
            coreContainer = initializer.initialize();
            System.out.println(coreContainer.getCoreNames());
        } catch (Exception e) {
            e.printStackTrace();
        }
        server = new EmbeddedSolrServer(coreContainer, "collection1");
        System.out.println("Initialized server in " + tim.stop() + "ms");
    }

    public static Result search() throws SolrServerException {
        Timer tim = new Timer();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setSortField("random_" + UUID.randomUUID().toString(), asc);
        QueryResponse rsp = server.query(query);
        List<Ad> adList = rsp.getBeans(Ad.class);
        System.out.println("Searched all ads in " + tim.stop() + "ms");
        return ok(ads.render(adList, rsp.getResults().getNumFound(), tim.stop()));
    }

    static long getCount() throws SolrServerException {
        Timer tim = new Timer();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setRows(0);
        QueryResponse rsp = server.query(query);
        System.out.println("found size of index in " + tim.stop() + "ms");
        return rsp.getResults().getNumFound();
    }

    public static Result facets(int numFacets) throws SolrServerException {
        Timer tim = new Timer();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.addFacetField("companyname");
        query.setFacet(true);
        query.setFacetLimit(numFacets);
        query.setRows(0);
        QueryResponse rsp = server.query(query);

        List<FacetField.Count> companies = rsp.getFacetField("companyname").getValues();

        System.out.println("Got " + companies.size() + " facets in " + tim.stop() + "ms");
        return ok(facets.render(companies, rsp.getResults().getNumFound(), tim.stop()));
    }

    public static Result add() throws IOException, SolrServerException {
        return addMany(1);
    }

    public static Result addMany(int many) throws IOException, SolrServerException {
        Timer tim = new Timer();
        List<Ad> ads = new ArrayList<>();
        for (int i = 0; i < many; i++) {
            ads.add(Ad.getAd());
        }
        server.addBeans(ads);
        server.commit();
        System.out.println("Added " + ads.size() + " ads in " + tim.stop() + "ms");
        return Application.index("Added " + ads.size() + " ads in " + tim.stop() + "ms");

    }

    public static Result removeAll() throws IOException, SolrServerException {
        Timer tim = new Timer();
        server.deleteByQuery("*:*");
        server.commit();
        System.out.println("Cleared index in " + tim.stop() + "ms");
        return Application.index("Cleared index in " + tim.stop() + "ms");
    }

}
