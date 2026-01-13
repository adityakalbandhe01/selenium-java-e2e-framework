package tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.BaseTest;

public class CheckboxAndAlertTest extends BaseTest {

    @BeforeMethod
    public void setUpTest() {
        setUp();
    }

    @Test
    public void handleCheckboxAndAlert() {

        // Open application
        driver.get("https://testautomationpractice.blogspot.com/");

        // Locate checkbox
        WebElement sundayCheckbox = driver.findElement(By.id("sunday"));

        // Select checkbox only if not already selected
        if (!sundayCheckbox.isSelected()) {
            sundayCheckbox.click();
        }

        // Click alert button
        driver.findElement(By.xpath("//button[@id='alertBtn']")).click();

        // Switch to alert
        Alert alert = driver.switchTo().alert();

        // Print alert text
        System.out.println("Alert text: " + alert.getText());

        // Accept alert
        alert.accept();
    }

    @AfterMethod
    public void tearDownTest() {
        tearDown();
    }
}