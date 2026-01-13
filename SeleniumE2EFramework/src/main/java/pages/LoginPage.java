package pages;

import org.openqa.selenium.*;
import utils.WaitUtils;

public class LoginPage {

    WebDriver driver;
    WaitUtils wait;

    By username = By.name("username");
    By password = By.name("password");
    By loginBtn = By.xpath("//button[@type='submit']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
    }

    public void login(String user, String pass) {
        wait.waitForVisibility(username).sendKeys(user);
        wait.waitForVisibility(password).sendKeys(pass);
        wait.waitForClick(loginBtn);
    }
}