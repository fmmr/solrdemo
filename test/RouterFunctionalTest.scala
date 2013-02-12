import org.specs2.mutable._

import play.api.libs.ws.WS
import play.api.test.{TestServer, FakeRequest}
import play.api.test.Helpers._

class RouterFunctionalTest extends Specification {
  "run in a server" in {
    running(TestServer(3333)) {
      await(WS.url("http://localhost:3333").get()).status must equalTo(OK)
    }
  }

//  "respond to the add Action" in {
//    val Some(result) = route(FakeRequest(GET, "/add"))
//    status(result) must equalTo(OK)
//    contentType(result) must beSome("text/html")
//    charset(result) must beSome("utf-8")
//    contentAsString(result) must contain("added")
//  }

//  "run in a browser" in {
//    running(TestServer(3333), HTMLUNIT) { browser =>
//
//      browser.goTo("http://localhost:3333")
//      browser.$("#title").getText must contain("FMR SOLR DEMO")
//
//      browser.$("a").click()
//
//      browser.url must equalTo("http://localhost:3333/add")
//      browser.$("#title").getText must contain("Added ad")
//
//    }
//  }
}
