import org.specs2.mutable._

import play.api.test._
import play.api.test.Helpers._

class ViewFunctionalTest extends Specification {

  // test test view
  "render index template" in {
    val html = views.html.index()

    contentType(html) must equalTo("text/html")
    contentAsString(html) must contain("FMR SOLR DEMO")
  }
}
