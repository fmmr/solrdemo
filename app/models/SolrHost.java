package models;

import java.net.MalformedURLException;
import java.net.URL;

import static play.data.validation.Constraints.Required;

/**
 * User: frerodla
 * Date: 14.02.13
 * Time: 11:31
 */
@SuppressWarnings("UnusedDeclaration")
public final class SolrHost {
    @Required
    public String host;
    @Required
    public int port;

    public static final SolrHost DEFAULT = new SolrHost("solr1.finntech.no", 12100);

    public SolrHost() {
    }

    public SolrHost(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public SolrHost(String host) throws MalformedURLException {
        URL url = new URL(host);
        this.host = url.getHost();
        this.port = url.getPort();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SolrHost solrHost = (SolrHost) o;
        return port == solrHost.port && !(host != null ? !host.equals(solrHost.host) : solrHost.host != null);

    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + port;
        return result;
    }

    @Override
    public String toString() {
        return "http://" + host + ':' + port;
    }
}
