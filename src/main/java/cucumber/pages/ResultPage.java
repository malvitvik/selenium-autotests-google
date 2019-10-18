package cucumber.pages;

import org.springframework.stereotype.Component;

@Component
public class ResultPage extends Page {
    public String getTitle() {
        return driver.getTitle();
    }
}
