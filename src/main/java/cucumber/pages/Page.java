package cucumber.pages;

import cucumber.framework.PropertiesFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

public abstract class Page {
    protected static final Logger LOG = LogManager.getLogger();
    @Autowired
    protected WebDriver driver;
    protected Wait<WebDriver> wait;
    private String url;

    @PostConstruct
    public void setUp() {
        url = PropertiesFile.getInstance().getProperty("url");

        this.wait = new WebDriverWait(driver, 5, 100);
    }

    protected void open(String url) {
        driver.get(url);
    }

    protected String getUrl() {
        return url;
    }

    protected WebElement $(String xpath, String... args) {
        return driver.findElement(By.xpath(String.format(xpath, args)));
    }

    protected WebElement $(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }


    protected List<WebElement> $$(By by) {
        return driver.findElements(by);
    }

    protected WebElement $(By by) {
        return driver.findElement(by);
    }
}
