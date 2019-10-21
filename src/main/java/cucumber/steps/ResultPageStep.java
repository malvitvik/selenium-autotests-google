package cucumber.steps;

import cucumber.api.java.en.Then;
import cucumber.pages.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class ResultPageStep extends Steps {

    @Autowired
    ResultPage resultPage;

    @Then("^the page title contains \"(.*?)\"$")
    public void containsDomainOnPages(String phrase) {
        String title = resultPage.getTitle();
        LOG.debug("The page title '" + title + "'");
        assertTrue("The page title '" + title + "' has no phrase: " + phrase, title.toLowerCase().contains(phrase));
    }
}