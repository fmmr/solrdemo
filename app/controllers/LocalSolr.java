package controllers;

import models.Ad;
import utils.Timer;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.core.CoreContainer;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.simple;
import views.html.ads;

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

    public static Result index() throws SolrServerException {
        Timer tim = new Timer();
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setSortField("random_" + UUID.randomUUID().toString(), asc);
        QueryResponse rsp = server.query(query);
        List<Ad> adList = rsp.getBeans(Ad.class);
        System.out.println("Searched all ads in " + tim.stop() + "ms");
        return ok(ads.render(adList, tim.stop()));
    }

    public static Result add() throws IOException, SolrServerException {
        Timer tim = new Timer();
        Ad ad = Ad.getAd();
        server.addBean(ad);
        server.commit();
        System.out.println("Added doc in " + tim.stop() + "ms");
        return ok(simple.render("Ad: " + ad.getId() + " added", "Added ad", tim.stop()));
    }

    public static Result removeAll() throws IOException, SolrServerException {
        Timer tim = new Timer();
        server.deleteByQuery("*:*");
        server.commit();
        System.out.println("Cleared index in " + tim.stop() + "ms");
        return ok(simple.render("Cleared index", "Cleared index", tim.stop()));
    }

    public static Result addMany() throws IOException, SolrServerException {
        Timer tim = new Timer();
        int many = 1000;
        List<Ad> ads = new ArrayList<>();
        for (int i = 0; i < many; i++) {
            ads.add(Ad.getAd());
        }
        server.addBeans(ads);
        server.commit();
        System.out.println("Added " + ads.size() + " ads in " + tim.stop() + "ms");
        return ok(simple.render("Added " + ads.size() + " ads", "Added ads", tim.stop()));
    }

}
