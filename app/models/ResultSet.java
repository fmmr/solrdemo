package models;

import java.util.Iterator;
import java.util.List;

/**
 * User: frerodla
 * Date: 15.02.13
 * Time: 15:24
 */
public class ResultSet implements Iterable<Ad> {

    public final List<Ad> ads;
    public final long count;

    public ResultSet(List<Ad> ads, long count) {
        this.ads = ads;
        this.count = count;
    }

    @Override
    public Iterator<Ad> iterator() {
        return ads.iterator();
    }
}
