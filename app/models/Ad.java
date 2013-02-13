package models;

import org.apache.solr.client.solrj.beans.Field;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.Random;
import java.util.UUID;

/**
 * User: frerodla
 * Date: 08.02.13
 * Time: 14:56
 */
public class Ad {

    @Field
    @JsonProperty
    public String id;

    @Field("heading")
    @JsonProperty
    public String title;

    //@Field
    @JsonProperty
    public double score;

    @Field
    @JsonProperty
    public String companyname;


    @Field
    @JsonProperty
    public String coordinate;

    public static Ad getAd() {
        Ad ad = new Ad();
        ad.id = UUID.randomUUID().toString();
        ad.companyname = (NAMES[new Random().nextInt(NAMES.length)]);
        ad.title = "Title for ad from company " + ad.companyname;
        return ad;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", companyname='" + companyname + '\'' +
                ", coordinate='" + coordinate + '\'' +
                '}';
    }

    public static final String[] NAMES = {"Jetbird", "Riffpad", "Dynagen", "Podpad", "Cogindo", "Muva", "Wikizio", "Bubblebuzz", "Browsepoint", "Brighttags", "Feedbird", "Oyonoodle", "Roodel", "Realworks", "Tambee", "Demizzy", "Leeloo", "Divadel", "Flipbox", "Realbridge", "Yaxo", "Buzzbug", "Tagstorm", "Kaymm", "Liveverse", "Wikinder", "Flashzone", "Leezz", "Dabfire", "Kagen", "Dynajo", "Mymbo", "Twigen", "Pixozu", "Topshare", "Linklinks", "Aicero", "Shuffleware", "Trimbee", "Eazzy", "Bluepath", "Podtype", "Linkwire", "Toppoint", "Digidrive", "Podtag", "Bubbleify", "Toppath", "Meedeo", "Snaptags", "Yamm", "Flipspan", "Skamm", "Mizio", "Cogijo", "Edgebox", "Mulane", "Tadoo", "Gigatags", "JumpXS", "Rooloo", "Riffdog", "Liveify", "Yakinti", "Feedvine", "Deminte", "Avanyx", "Brainster", "Zatri", "Jabberlounge", "Topiclounge", "Yakixo", "Kwilane", "Gabcube", "Wordmix", "Mymia", "Fiveware", "Kaveo", "Wordpulse", "Feedpedia", "Skanu", "Camiva", "Bubblepoint", "Podbug", "Centiyo", "Zante", "Pixoveo", "Kijo", "Quatz", "Abamia", "Kado", "Nclub", "Cogiyo", "Feedbeat", "Lavu", "Mylia", "Avadel", "Chatwire", "Devvine", "Twitterclub", "Leexo", "Cogizio", "Quimba", "Realware", "Realpoint", "Ooboo", "Demiloo", "Mulia", "BuzzXS", "Yacero", "Twiloo", "Bluecube", "Tekbuzz"};

}
