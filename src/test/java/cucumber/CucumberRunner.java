package cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.framework.BrowserFactory;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {},
        features = {"src/test/resources/"},
        plugin = {"pretty", "html:target/html", "json:target/cucumber-report.json"},
        glue = "cucumber.steps")
@ContextConfiguration(locations = "classpath:/cucumber.xml", classes = BrowserFactory.class)
public class CucumberRunner {

}
