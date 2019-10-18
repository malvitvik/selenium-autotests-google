package cucumber.framework;

import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Configuration
public class BrowserFactory {
    private final String browser;
    private final PropertiesFile propertiesFile;
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

    @Bean
    public WebDriver getDriver() {
        WebDriver driver = queueDriver.poll();

        if (driver == null) {
            driver = driverMap.get(browser).get();
        }

        long loadTimeout = Long.parseLong(propertiesFile.getProperty("loadTimeout"));
        long implicitlyWait = Long.parseLong(propertiesFile.getProperty("implicitlyWait"));

        WebDriver.Timeouts timeouts = driver.manage().timeouts();
        timeouts.pageLoadTimeout(loadTimeout, TimeUnit.SECONDS);
        timeouts.implicitlyWait(implicitlyWait, TimeUnit.SECONDS);

        String resolution = propertiesFile.getProperty("resolution");

        switch (resolution) {
            case "max":
                driver.manage().window().maximize();
                break;

            case "fullscreen":
                driver.manage().window().fullscreen();
                break;

            default:
                String[] dimensions = resolution.split("x");
                assert (dimensions.length == 2);
                driver.manage().window().setSize(new Dimension(Integer.parseInt(dimensions[0]), Integer.parseInt(dimensions[1])));
                break;
        }

        return driver;
    }

    public void release(@NotNull WebDriver driver) {
        queueDriver.add(driver);
    }

    public void quitAll() {
        queueDriver.forEach(WebDriver::quit);
    }
}
