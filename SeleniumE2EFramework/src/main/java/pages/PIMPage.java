package pages;

import org.openqa.selenium.*;
import utils.WaitUtils;

public class PIMPage {

    WebDriver driver;
    WaitUtils wait;

    By addEmployeeBtn = By.xpath("//a[text()='Add Employee']");
    By firstName = By.name("firstName");
    By lastName = By.name("lastName");
    By saveBtn = By.xpath("//button[@type='submit']");

    public PIMPage(WebDriver driver) {
        this.driver = driver;
        wait = new WaitUtils(driver);
    }

    public void addEmployee(String fname, String lname) {
        wait.waitForClick(addEmployeeBtn);
        wait.waitForVisibility(firstName).sendKeys(fname);
        wait.waitForVisibility(lastName).sendKeys(lname);
        wait.waitForClick(saveBtn);
    }
}