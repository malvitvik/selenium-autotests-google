package cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.pages.GoogleHome;
import org.springframework.beans.factory.annotation.Autowired;

public class GoogleHomeSteps extends Steps {

    @Autowired
    GoogleHome googleHome;

    @Given("^I open Google page$")
    public void openGoogleHome() {
        googleHome.open();
    }

    @When("^I search for \"(.*?)\"$")
    public void searchForPhrase(String phrase) {
        googleHome.doSearch(phrase);
    }
}
