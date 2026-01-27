package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleE2ETest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            // Open application
            driver.manage().window().maximize();
            driver.get("https://www.saucedemo.com/");

            // Login
            driver.findElement(By.id("user-name"))
                    .sendKeys("standard_user");
            driver.findElement(By.id("password"))
                    .sendKeys("secret_sauce");
            driver.findElement(By.id("login-button"))
                    .click();

            // Simple assertion (check Products text)
            String title = driver.findElement(By.className("title")).getText();

            if (title.equals("Products")) {
                System.out.println("✅ E2E Test Passed");
            } else {
                System.out.println("❌ E2E Test Failed");
            }

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}