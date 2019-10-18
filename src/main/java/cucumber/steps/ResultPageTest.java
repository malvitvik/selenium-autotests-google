package cucumber.steps;

import cucumber.api.java.en.Then;
import cucumber.pages.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class ResultPageTest extends Steps {

    @Autowired
    ResultPage resultPage;

    @Then("^the page title contains \"(.*?)\"$")
    public void containsDomainOnPages(String phrase) {
        String title = resultPage.getTitle();
        assertTrue("The page title '" + title + "' has no phrase: " + phrase, title.toLowerCase().contains(phrase));
    }
}
