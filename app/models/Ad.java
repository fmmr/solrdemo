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
    String id;

    @Field("heading")
    @JsonProperty
    String title;

    //@Field
    @JsonProperty
    double score;

    @Field
    @JsonProperty
    String companyname;

    @Field
    @JsonProperty
    String fulladdress;

    @Field
    @JsonProperty
    String coordinate;

    public static Ad getAd() {
        Ad ad = new Ad();
        ad.setId(UUID.randomUUID().toString());
        ad.setCompanyname((NAMES[new Random().nextInt(NAMES.length)]));
        return ad;
    }

    @Override
    public String toString() {
        return "Ad{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", score=" + score +
                ", companyname='" + companyname + '\'' +
                ", fulladdress='" + fulladdress + '\'' +
                ", coordinate='" + coordinate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getFulladdress() {
        return fulladdress;
    }

    public void setFulladdress(String fulladdress) {
        this.fulladdress = fulladdress;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }


    public static final String[] NAMES = {"Jetbird", "Riffpad", "Dynagen", "Podpad", "Cogindo", "Muva", "Wikizio", "Bubblebuzz", "Browsepoint", "Brighttags", "Feedbird", "Oyonoodle", "Roodel", "Realworks", "Tambee", "Demizzy", "Leeloo", "Divadel", "Flipbox", "Realbridge", "Yaxo", "Buzzbug", "Tagstorm", "Kaymm", "Liveverse", "Wikinder", "Flashzone", "Leezz", "Dabfire", "Kagen", "Dynajo", "Mymbo", "Twigen", "Pixozu", "Topshare", "Linklinks", "Aicero", "Shuffleware", "Trimbee", "Eazzy", "Bluepath", "Podtype", "Linkwire", "Toppoint", "Digidrive", "Podtag", "Bubbleify", "Toppath", "Meedeo", "Snaptags", "Yamm", "Flipspan", "Skamm", "Mizio", "Cogijo", "Edgebox", "Mulane", "Tadoo", "Gigatags", "JumpXS", "Rooloo", "Riffdog", "Liveify", "Yakinti", "Feedvine", "Deminte", "Avanyx", "Brainster", "Zatri", "Jabberlounge", "Topiclounge", "Yakixo", "Kwilane", "Gabcube", "Wordmix", "Mymia", "Fiveware", "Kaveo", "Wordpulse", "Feedpedia", "Skanu", "Camiva", "Bubblepoint", "Podbug", "Centiyo", "Zante", "Pixoveo", "Kijo", "Quatz", "Abamia", "Kado", "Nclub", "Cogiyo", "Feedbeat", "Lavu", "Mylia", "Avadel", "Chatwire", "Devvine", "Twitterclub", "Leexo", "Cogizio", "Quimba", "Realware", "Realpoint", "Ooboo", "Demiloo", "Mulia", "BuzzXS", "Yacero", "Twiloo", "Bluecube", "Tekbuzz"};

}
