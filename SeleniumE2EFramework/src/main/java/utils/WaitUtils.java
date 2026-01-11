package utils;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

public class WaitUtils {

    WebDriver driver;
    WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
}