import controllers.RemoteSolr;
import models.SolrHost;
import play.Application;
import play.GlobalSettings;
import play.Logger;

/**
 * User: frerodla
 * Date: 13.02.13
 * Time: 16:26
 */
public class Global extends GlobalSettings {


    @Override
    public void onStart(Application app) {
        SolrHost solr = SolrHost.DEFAULT;
        Logger.info("Setting remote solr to " + solr);
        RemoteSolr.setSolr(solr);
        Logger.info("SOLRDEMO STARTED");
    }

    @Override
    public void onStop(Application app) {
        Logger.info("SOLRDEMO WILL STOP");
    }

}