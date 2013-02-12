import org.specs2.mutable._

import play.api.test.Helpers._

class ControllerFunctionalTest extends Specification {
  // test controller
  "respond to the index Action" in {
    val result = controllers.Application.index().getWrappedResult()
    status(result) must equalTo(OK)
    contentType(result) must beSome("text/html")
    charset(result) must beSome("utf-8")
    contentAsString(result) must contain("FMR SOLR DEMO")
  }
}
