package pages;

import org.openqa.selenium.*;
import utils.WaitUtils;

public class DashboardPage {

    WebDriver driver;
    WaitUtils wait;

    By pimMenu = By.xpath("//span[text()='PIM']");

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
    }

    public void goToPIM() {
        wait.waitForClick(pimMenu);
    }
}