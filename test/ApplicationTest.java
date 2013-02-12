import models.Ad;
import org.junit.Test;
import play.mvc.Content;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;


/**
 * Simple (JUnit) tests that can call all parts of a play app.
 * If you are interested in mocking a whole application, see the wiki for more details.
 */
public class ApplicationTest {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertThat(a).isEqualTo(2);
    }

    @Test
    public void renderTemplate() {
        List<Ad> list = new ArrayList<>();
        Ad ad = Ad.getAd();
        list.add(ad);
        list.add(Ad.getAd());

        Content html = views.html.index.render(list);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains(ad.getCompanyname());
    }


}
