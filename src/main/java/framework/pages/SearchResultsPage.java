package framework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultsPage extends Page {
    private final By bySearchResults = By.xpath("//div[@class='r']/a[not(@class='fl')]");
    private final By byNext = By.id("pnnext");
    private final By byPrev = By.id("pnprev");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage open() {
        return this;
    }

    public ResultPage openResultPage(int index) {
        LOG.debug("Open " + index + " page from results.");
        getResults().get(index).click();
        return new ResultPage(driver);
    }

    public List<WebElement> getResults() {
        return driver.findElements(bySearchResults);
    }

    public void prevPage() {
        LOG.debug("Open previous page with results.");
        driver.findElement(byPrev).click();
    }

    public void nextPage() {
        LOG.debug("Open next page with results.");
        driver.findElement(byNext).click();
    }

    public boolean containsResult(String text) {
        return driver.findElements(By.partialLinkText(text)).size() != 0;
    }

    public boolean containsResult(String text, int pages) {
        if (pages <= 0) {
            return containsResult(text);
        }

        for (int i = 1; i <= pages; i++) {
            if (containsResult(text)) {
                return true;
            }

            nextPage();
        }

        return false;
    }
}
