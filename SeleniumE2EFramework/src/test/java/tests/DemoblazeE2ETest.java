package tests;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoblazeE2ETest {

    public static void main(String[] args) throws InterruptedException {

        // 1️⃣ Launch Browser
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // 2️⃣ Open Demoblaze
        driver.get("https://www.demoblaze.com/");

        // 3️⃣ Login
        driver.findElement(By.id("login2")).click();
        Thread.sleep(2000);

        driver.findElement(By.id("loginusername")).sendKeys("aditya_test");
        driver.findElement(By.id("loginpassword")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[text()='Log in']")).click();
        Thread.sleep(3000);

        // 4️⃣ Select Product
        driver.findElement(By.linkText("Samsung galaxy s6")).click();
        Thread.sleep(2000);

        // 5️⃣ Add to Cart
        driver.findElement(By.linkText("Add to cart")).click();
        Thread.sleep(2000);

        Alert alert = driver.switchTo().alert();
        alert.accept();

        // 6️⃣ Go to Cart
        driver.findElement(By.id("cartur")).click();
        Thread.sleep(3000);

        // 7️⃣ Place Order
        driver.findElement(By.xpath("//button[text()='Place Order']")).click();
        Thread.sleep(2000);

        // 8️⃣ Fill Order Details
        driver.findElement(By.id("name")).sendKeys("Aditya");
        driver.findElement(By.id("country")).sendKeys("India");
        driver.findElement(By.id("city")).sendKeys("Pune");
        driver.findElement(By.id("card")).sendKeys("4111111111111111");
        driver.findElement(By.id("month")).sendKeys("12");
        driver.findElement(By.id("year")).sendKeys("2026");

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();
        Thread.sleep(2000);

        // 9️⃣ Verify Confirmation
        boolean isConfirmed = driver.findElement(
                By.xpath("//h2[text()='Thank you for your purchase!']")
        ).isDisplayed();

        if (isConfirmed) {
            System.out.println("✅ Order placed successfully!");
        } else {
            System.out.println("❌ Order failed!");
        }

        // 🔚 Close Browser
        driver.quit();
    }
}