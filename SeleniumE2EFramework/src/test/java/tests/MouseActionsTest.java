package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MouseActionsTest {

    WebDriver driver;
    Actions actions;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://demoqa.com/menu");
        actions = new Actions(driver);
    }

    @Test
    public void mouseHoverTest() {
    	

        WebElement mainItem = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        WebElement subItem = driver.findElement(By.xpath("//a[text()='SUB SUB LIST »']"));
        WebElement subSubItem = driver.findElement(By.xpath("//a[text()='Sub Sub Item 2']"));

        actions.moveToElement(mainItem)
               .moveToElement(subItem)
               .moveToElement(subSubItem)
               .click()
               .build()
               .perform();
        
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}