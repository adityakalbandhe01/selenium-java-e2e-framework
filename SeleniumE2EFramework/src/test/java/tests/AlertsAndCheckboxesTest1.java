package tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsAndCheckboxesTest1 {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://testautomationpractice.blogspot.com/");

        /* =======================
           SIMPLE ALERT
           ======================= */
        WebElement alertBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@onclick='myFunction()']")));

        alertBtn.click();

        Alert simpleAlert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println(simpleAlert.getText());
        simpleAlert.accept();

        /* =======================
           CONFIRM ALERT
           ======================= */
        driver.findElement(By.xpath("//button[@onclick='myFunctionConfirm()']")).click();

        Alert confirmAlert = wait.until(ExpectedConditions.alertIsPresent());
        confirmAlert.dismiss();

        //=======================
          // PROMPT ALERT handling
       //    ======================= */
        driver.findElement(By.xpath("//button[@onclick='myFunctionPrompt()']")).click();

        Alert promptAlert = wait.until(ExpectedConditions.alertIsPresent());
        promptAlert.sendKeys("Aditya");
        promptAlert.accept();

        /* =======================
           CHECKBOXES handling
           ======================= */
        List<WebElement> checkboxes =
                driver.findElements(By.xpath("//input[@type='checkbox' and contains(@id,'day')]"));

        for (WebElement cb : checkboxes) {
            if (!cb.isSelected()) {
                cb.click();
            }
        }

        Thread.sleep(2000);
        driver.quit();
    }
}