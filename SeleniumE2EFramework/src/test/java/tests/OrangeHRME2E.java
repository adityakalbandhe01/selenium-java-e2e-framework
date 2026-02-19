package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrangeHRME2E {

    @Test
    public void orangeHRMFlow() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {

            // 1️⃣ Launch Application
            driver.get("https://opensource-demo.orangehrmlive.com/");
            Assert.assertTrue(driver.getTitle().contains("OrangeHRM"));

            // 2️⃣ Login
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")))
                    .sendKeys("Admin");

            driver.findElement(By.name("password"))
                    .sendKeys("admin123");

            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // 3️⃣ Verify Dashboard
            WebElement dashboard = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h6[text()='Dashboard']"))
            );
            Assert.assertTrue(dashboard.isDisplayed(), "Login Failed");

            // 4️⃣ Navigate to PIM
            driver.findElement(By.xpath("//span[text()='PIM']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h6[text()='PIM']")));

            // 5️⃣ Click Add Employee
            driver.findElement(By.xpath("//button[text()=' Add ']")).click();

            // 6️⃣ Enter Employee Details
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("firstName")))
                    .sendKeys("John");

            driver.findElement(By.name("lastName"))
                    .sendKeys("Tester");

            // 7️⃣ Save Employee
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            // 8️⃣ Verify Employee Created
            WebElement personalDetails = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h6[text()='Personal Details']"))
            );
            Assert.assertTrue(personalDetails.isDisplayed(),
                    "Employee creation failed");

            // 9️⃣ Logout
            driver.findElement(By.xpath("//span[@class='oxd-userdropdown-tab']")).click();
            driver.findElement(By.xpath("//a[text()='Logout']")).click();

            // 🔟 Verify Logout
            Assert.assertTrue(driver.getCurrentUrl().contains("login"));

        } finally {
            driver.quit();
        }
    }
}