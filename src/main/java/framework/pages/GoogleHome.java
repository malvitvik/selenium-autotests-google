package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleHome extends Page {
    private By bySearchField = By.name("q");

    public GoogleHome(WebDriver driver) {
        super(driver);
    }

    @Override
    public GoogleHome open() {
        driver.get(getUrl());
        LOG.debug("Google Home is opened by url: " + getUrl());
        return this;
    }

    public SearchResultsPage doSearch(String searchPhrase) {
        WebElement searchField = driver.findElement(bySearchField);
        searchField.sendKeys(searchPhrase);
        searchField.submit();
        LOG.debug("Google Home: do search by phrase: " + searchPhrase);
        return new SearchResultsPage(driver);
    }
}
