package utils;

import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

/**
 * User: frerodla
 * Date: 13.02.13
 * Time: 16:33
 */
public class TimerAction extends Action<Timing> {
    private static final Logger.ALogger LOG = Logger.of("timing");

    public Result call(Http.Context ctx) throws Throwable {
        Timer timer = new Timer();
        Result call = delegate.call(ctx);
        LOG.info(ctx.request().toString() + ", took: " + timer.stop() + "ms");
        return call;
    }
}