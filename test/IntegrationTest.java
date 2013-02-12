import org.junit.Test;
import play.libs.F.Callback;
import play.test.TestBrowser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class IntegrationTest {

    //Ad: 3c931309-6476-41f5-9cd6-63a3a259a6ed added in 140ms
    private static final Pattern ADD_ADID = Pattern.compile("Ad: (.*) added in .*");

    @Test
    public void test_add() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/add");
                assertThat(browser.pageSource()).contains("added");
            }
        });
    }


    @Test
    public void test_add_and_search() {
        running(testServer(3333, fakeApplication(inMemoryDatabase())), HTMLUNIT, new Callback<TestBrowser>() {
            public void invoke(TestBrowser browser) {
                browser.goTo("http://localhost:3333/add");
                String content = browser.pageSource();
                Matcher matcher = ADD_ADID.matcher(content);
                matcher.matches();
                String adId = matcher.group(1);

                browser.goTo("http://localhost:3333");
                assertThat(browser.pageSource()).contains(adId);
            }
        });
    }
}
