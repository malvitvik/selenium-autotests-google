package cucumber.steps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

public class Steps {
    protected static final Logger LOG = LogManager.getLogger();

    @Autowired
    WebDriver driver;
}