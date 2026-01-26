package tests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class DemoQA_E2E_Test {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // =====================
            // 1️⃣ Launch Application
            // =====================
            driver.manage().window().maximize();
            driver.get("https://demoqa.com");

            Assert.assertTrue(driver.getTitle().contains("DEMOQA"), "Home page title mismatch");

            // =====================
            // 2️⃣ Forms – Fill Form
            // =====================
            driver.findElement(By.xpath("//h5[text()='Forms']")).click();
            driver.findElement(By.xpath("//span[text()='Practice Form']")).click();

            driver.findElement(By.id("firstName")).sendKeys("Aditya");
            driver.findElement(By.id("lastName")).sendKeys("Kalbandhe");
            driver.findElement(By.id("userEmail")).sendKeys("test@test.com");

            driver.findElement(By.xpath("//label[text()='Male']")).click();
            driver.findElement(By.id("userNumber")).sendKeys("9999999999");

            // Submit
            driver.findElement(By.id("submit")).click();

            WebElement confirmation =
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")));

            Assert.assertEquals(confirmation.getText(), "Thanks for submitting the form");

            // =====================
            // 3️⃣ Alerts
            // =====================
            driver.get("https://demoqa.com/alerts");

            driver.findElement(By.id("alertButton")).click();
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertEquals(alert.getText(), "You clicked a button");
            alert.accept();

            // =====================
            // 4️⃣ Frames
            // =====================
            driver.get("https://demoqa.com/frames");

            driver.switchTo().frame("frame1");
            String frameText = driver.findElement(By.id("sampleHeading")).getText();
            Assert.assertEquals(frameText, "This is a sample page");
            driver.switchTo().defaultContent();

            // =====================
            // 5️⃣ Nested Frames
            // =====================
            driver.get("https://demoqa.com/nestedframes");

            driver.switchTo().frame("frame1");
            driver.switchTo().frame(0);
            String nestedText = driver.findElement(By.tagName("p")).getText();
            Assert.assertEquals(nestedText, "Child Iframe");
            driver.switchTo().defaultContent();

            // =====================
            // 6️⃣ Window Handling
            // =====================
            driver.get("https://demoqa.com/browser-windows");

            String parentWindow = driver.getWindowHandle();
            driver.findElement(By.id("windowButton")).click();

            Set<String> windows = driver.getWindowHandles();
            for (String win : windows) {
                if (!win.equals(parentWindow)) {
                    driver.switchTo().window(win);
                    Assert.assertTrue(driver.getPageSource().contains("This is a sample page"));
                    driver.close();
                }
            }
            driver.switchTo().window(parentWindow);

            // =====================
            // 7️⃣ Dropdown (Select)
            // =====================
            driver.get("https://demoqa.com/select-menu");

            WebElement dropdown = driver.findElement(By.id("oldSelectMenu"));
            Select select = new Select(dropdown);
            select.selectByVisibleText("Blue");

            Assert.assertEquals(select.getFirstSelectedOption().getText(), "Blue");

            System.out.println("✅ E2E Test Completed Successfully");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}