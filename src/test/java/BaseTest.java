import framework.DriverFactory;
import framework.pages.GoogleHome;
import framework.pages.SearchResultsPage;
import org.junit.*;
import org.openqa.selenium.WebDriver;

public class BaseTest extends Assert {
    static DriverFactory driverFactory;

    @BeforeClass
    public static void setupClass() {
        driverFactory = new DriverFactory();
    }

    @AfterClass
    public static void tearDownClass() {
        driverFactory.closeAll();
    }

    @Before
    public void setup() {
    }

    @Test
    public void testFirstLinkInSearch() {
        String phrase = "automation";
        WebDriver driver = driverFactory.createDriver();
        SearchResultsPage searchResultsPage = new GoogleHome(driver).open().doSearch(phrase);

        String title = searchResultsPage.openResultPage(0).getTitle();
        driverFactory.releaseDriver(driver);
        assertTrue("The page title '" + title + "' has no phrase: " + phrase, title.toLowerCase().contains(phrase));
    }

    @Test
    public void Test() {
        String phrase = "automation";
        WebDriver driver = driverFactory.createDriver();
        SearchResultsPage searchResultsPage = new GoogleHome(driver).open().doSearch(phrase);

        assertTrue(searchResultsPage.containsResult("testautomationday.com", 5));
        driverFactory.releaseDriver(driver);
    }

}