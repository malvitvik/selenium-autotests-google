package cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchResultsPage extends Page {
    private final By bySearchResults = By.xpath("//div[@class='r']/a[not(@class='fl')]");
    private final By byNext = By.id("pnnext");
    private final By byPrev = By.id("pnprev");

    public SearchResultsPage open() {
        return this;
    }

    public ResultPage openResultPage(int index) {
        LOG.debug("Open " + index + " page from results.");
        getResults().get(index).click();
        return new ResultPage();
    }

    public List<WebElement> getResults() {
        return $$(bySearchResults);
    }

    public void prevPage() {
        LOG.debug("Open previous page with results.");
        $(byPrev).click();
    }

    public void nextPage() {
        LOG.debug("Open next page with results.");
        $(byNext).click();
    }

    public boolean containsResult(String text) {
        return $$(By.partialLinkText(text)).size() != 0;
    }

    public boolean containsResult(String text, int pages) {
        if (pages <= 0) {
            return containsResult(text);
        }

        for (int i = 1; i <= pages; i++) {
            if (containsResult(text)) {
                return true;
            }

            $$(byNext).stream()
                    .findFirst()
                    .orElseThrow(() -> new AssertionError("The last search page is reached."))
                    .click();
        }

        return false;
    }
}
