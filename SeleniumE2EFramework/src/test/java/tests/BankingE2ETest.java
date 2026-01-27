package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BankingE2ETest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // 1️⃣ Launch application
            driver.manage().window().maximize();
            driver.get("https://demo.guru99.com/V4/");

            // 2️⃣ Login
            driver.findElement(By.name("uid")).sendKeys("mngr652552");
            driver.findElement(By.name("password")).sendKeys("gEgydUg");
            driver.findElement(By.name("btnLogin")).click();

            // 3️⃣ Handle invalid login alert (VERY IMPORTANT)
            try {
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                System.out.println("❌ Login Failed Alert: " + alert.getText());
                alert.accept();
                return; // stop test if login fails
            } catch (Exception e) {
                // No alert → login successful
                System.out.println("✅ Login successful");
            }

            // 4️⃣ Assertion – Manager ID visible
            WebElement managerId = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//td[contains(text(),'Manger Id')]"))
            );
            Assert.assertTrue(managerId.isDisplayed(), "Manager ID not visible");

            // 5️⃣ Navigate to New Customer
            driver.findElement(By.linkText("New Customer")).click();

            // 6️⃣ Fill customer form
            driver.findElement(By.name("name")).sendKeys("Aditya QA");
            driver.findElement(By.xpath("//input[@value='m']")).click();
            driver.findElement(By.name("dob")).sendKeys("01011998");
            driver.findElement(By.name("addr")).sendKeys("Pune Maharashtra");
            driver.findElement(By.name("city")).sendKeys("Pune");
            driver.findElement(By.name("state")).sendKeys("MH");
            driver.findElement(By.name("pinno")).sendKeys("411001");
            driver.findElement(By.name("telephoneno")).sendKeys("9876543210");

            String email = "qa" + System.currentTimeMillis() + "@mail.com";
            driver.findElement(By.name("emailid")).sendKeys(email);
            driver.findElement(By.name("password")).sendKeys("Test@123");

            // 7️⃣ Submit
            driver.findElement(By.name("sub")).click();

            // 8️⃣ Assertion – Customer created
            WebElement successMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//p[text()='Customer Registered Successfully!!!']"))
            );

            Assert.assertEquals(
                    successMsg.getText(),
                    "Customer Registered Successfully!!!"
            );

            System.out.println("✅ Banking E2E Test Passed");

        } catch (Exception e) {
            System.out.println("❌ Test Failed: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}