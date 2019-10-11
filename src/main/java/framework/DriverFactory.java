package framework;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private Map<String, Queue<WebDriver>> driverMap = new ConcurrentHashMap<>();
    private PropertiesFile propertiesFile;

    public DriverFactory() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        driverMap.put("chrome", new LinkedList<>());
        driverMap.put("firefox", new LinkedList<>());

        propertiesFile = PropertiesFile.getInstance();
        propertiesFile.readProperties();
    }

    public WebDriver createDriver() {
        String browser = propertiesFile.getProperty("browser");
        WebDriver d = driverMap.get(browser).poll();
        WebDriver driver = Objects.isNull(d) ? addEventHandler(new ChromeDriver()) : d;

        long pageLoadSec = Long.parseLong(propertiesFile.getProperty("loadTimeout"));
        driver.manage().timeouts().pageLoadTimeout(pageLoadSec, TimeUnit.SECONDS);

        String size = propertiesFile.getProperty("resolution");

        if ("max".equalsIgnoreCase(size)) {
            driver.manage().window().maximize();
        } else {
            String[] sizes = size.split(",");
            driver.manage().window().setSize(new Dimension(Integer.parseInt(sizes[0]), Integer.parseInt(sizes[1])));
        }

        return driver;
    }

    public void releaseDriver(WebDriver driver) {
        driverMap.get(propertiesFile.getProperty("browser")).add(driver);
    }

    public void closeAll() {
        driverMap.values().forEach(queue -> queue.forEach(WebDriver::close));
    }

    private WebDriver addEventHandler(WebDriver driver) {
        EventFiringWebDriver eventDriver = new EventFiringWebDriver(driver);
        eventDriver.register(new EventHandler());
        return eventDriver;
    }
}