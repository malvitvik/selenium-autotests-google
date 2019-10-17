import framework.BrowserFactory;
import framework.pages.GoogleHome;
import framework.pages.SearchResultsPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class BaseTest {

    static BrowserFactory browserFactory;

    protected WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        browserFactory = new BrowserFactory();
    }

    @AfterClass
    public static void teardownClass() {
        browserFactory.quitAll();
    }

    @Before
    public void setup() {
        driver = browserFactory.getDriver();
    }

    @After
    public void teardown() {
        browserFactory.release(driver);
    }

    @Test
    public void testFirstLinkInSearch() {
//        String phrase = "automation";
        String phrase = "jhkjlktfougyhjipkopiugyjhkjblnjl";
        SearchResultsPage searchResultsPage = new GoogleHome(driver).open().doSearch(phrase);

        assertNotEquals("Search page has no results", searchResultsPage.getResults().size(), 0);
        String title = searchResultsPage.openResultPage(0).getTitle();
        assertTrue("The page title '" + title + "' has no phrase: " + phrase, title.toLowerCase().contains(phrase));
    }

    @Test
    public void Test() {
//        String phrase = "automation";
        String phrase = "user ajhddr";
        SearchResultsPage searchResultsPage = new GoogleHome(driver).open().doSearch(phrase);

        assertNotEquals("Search page has no results", searchResultsPage.getResults().size(), 0);
        assertTrue("'testautomationday.com' hasn't found on first 5 pages.", searchResultsPage.containsResult("testautomationday.com", 5));
    }

}