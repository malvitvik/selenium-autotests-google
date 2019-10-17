package framework;

import com.sun.istack.internal.NotNull;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Supplier;

public class BrowserFactory {
    private String browser;
    private PropertiesFile propertiesFile;
    private Map<String, Supplier<WebDriver>> driverMap = new ConcurrentHashMap<>();
    private Queue<WebDriver> queueDriver = new LinkedBlockingQueue<>();

    public BrowserFactory() {
        driverMap.put("chrome", ChromeDriver::new);
        driverMap.put("firefox", FirefoxDriver::new);
        driverMap.put("opera", OperaDriver::new);
        driverMap.put("ie", InternetExplorerDriver::new);
        driverMap.put("edge", EdgeDriver::new);

        propertiesFile = PropertiesFile.getInstance();
        propertiesFile.readProperties();
        browser = propertiesFile.getProperty("browser");

        WebDriverManager.getInstance(DriverManagerType.valueOf(browser.toUpperCase())).setup();
    }

    public WebDriver getDriver() {
        WebDriver driver = queueDriver.poll();

        if (driver == null) {
            driver = driverMap.get(browser).get();
        }

        return driver;
    }

    public void release(@NotNull WebDriver driver) {
        if (driver != null) {
            queueDriver.add(driver);
        }
    }

    public void quitAll() {
        queueDriver.forEach(WebDriver::quit);
    }
}
