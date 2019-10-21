package cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class GoogleHome extends Page {
    private By bySearchField = By.name("q");

    public GoogleHome open() {
        open(getUrl());
        LOG.debug("Google Home is opened by url: " + getUrl());
        return this;
    }

    public SearchResultsPage doSearch(String searchPhrase) {
        WebElement searchField = $(bySearchField);
        searchField.sendKeys(searchPhrase);
        searchField.submit();
        LOG.debug("Google Home: do search by phrase: " + searchPhrase);
        return new SearchResultsPage();
    }
}