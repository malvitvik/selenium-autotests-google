package framework.pages;

import framework.PropertiesFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
    protected static final Logger LOG = LogManager.getLogger();
    protected final WebDriver driver;
    protected final Wait<WebDriver> wait;
    private String url;

    public Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5, 100);
        url = PropertiesFile.getInstance().getProperty("url");
    }

    public abstract Page open();

    protected String getUrl() {
        return url;
    }
}
