package framework.pages;

import org.openqa.selenium.WebDriver;

public class ResultPage extends Page {
    public ResultPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public Page open() {
        return this;
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
