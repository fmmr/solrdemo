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
        Logger.info("SOLRDEMO STARTED");
    }

    @Override
    public void onStop(Application app) {
        Logger.info("SOLRDEMO WILL STOP");
    }
}