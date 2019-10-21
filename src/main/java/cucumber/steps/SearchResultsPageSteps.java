package cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.pages.SearchResultsPage;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.NoSuchElementException;

import static org.junit.Assert.fail;

public class SearchResultsPageSteps extends Steps {
    @Autowired
    SearchResultsPage searchResultsPage;

    @And("open {int} link in search result")
    public void openResultPage(int link) {
        Assert.assertNotEquals("Page has no results", 0, searchResultsPage.getResults().size());
        searchResultsPage.openResultPage(link);
    }

    @Then("{string} domain is displayed on pages from {int} to {int}")
    public void containsDomainOnPages(String domain, int fromPage, int toPage) {
        String domainWithoutQuotes = domain.replace('"', '\u0000');

        for (int i = fromPage; i <= toPage; i++) {
            Assert.assertNotEquals("Page has no results", 0, searchResultsPage.getResults().size());
            if (searchResultsPage.containsResult(domainWithoutQuotes)) {
                LOG.debug(domain + " is found on page: " + i);
                return;
            }

            try {
                searchResultsPage.nextPage();
            } catch (NoSuchElementException e) {
                String message = "The last page is reached. Page: " + i;
                LOG.error(message);
                fail(message);
            }
        }

        String message = domain + " isn't displayed on pages from " + fromPage + " to " + toPage;
        LOG.error(message);
        fail(message);
    }
}