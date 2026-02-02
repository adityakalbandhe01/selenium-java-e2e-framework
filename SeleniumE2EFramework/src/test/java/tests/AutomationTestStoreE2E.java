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

public class AutomationTestStoreE2E {

    @Test
    public void e2eAutomationTestStore() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1️⃣ Launch site
            driver.get("https://automationteststore.com/");
            Assert.assertTrue(driver.getTitle().contains("Automation Test Store"),
                    "Home page not loaded");

            // 2️⃣ Click Login
            driver.findElement(By.linkText("Login or register")).click();

            // 3️⃣ Enter Login Details
            driver.findElement(By.id("loginFrm_loginname"))
                    .sendKeys("testuser123");   // demo creds
            driver.findElement(By.id("loginFrm_password"))
                    .sendKeys("Test@123");

            driver.findElement(By.xpath("//button[@title='Login']")).click();

            // 4️⃣ Verify Login Success
            WebElement welcomeMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(text(),'Welcome')]"))
            );
            Assert.assertTrue(welcomeMsg.isDisplayed(), "Login failed");

            // 5️⃣ Navigate to category (Skincare)
            driver.findElement(By.xpath("//a[text()='Skincare']")).click();

            // 6️⃣ Select first product
            driver.findElement(By.xpath("(//a[@class='prdocutname'])[1]")).click();

            // 7️⃣ Add to Cart
            driver.findElement(By.xpath("//a[@title='Add to Cart']")).click();

            // 8️⃣ Verify product added
            WebElement successMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector(".alert-success"))
            );
            Assert.assertTrue(successMsg.getText().contains("added to your shopping cart"),
                    "Product not added to cart");

            // 9️⃣ Logout
            driver.findElement(By.linkText("Logout")).click();

            // 🔟 Verify Logout
            Assert.assertTrue(driver.getCurrentUrl().contains("logout"),
                    "Logout failed");

        } finally {
            driver.quit();
        }
    }
}