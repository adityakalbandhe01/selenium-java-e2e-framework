package com.spicejet.tests;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sun.tools.javac.util.Assert;

public class SpiceJetCalendarTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://www.spicejet.com/");
    }

    @Test
    public void verifyFutureDateSelection() {

        // From city
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='From']/following-sibling::div/input")))
                .sendKeys("Mum");

        // To city
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[text()='To']/following-sibling::div/input")))
                .sendKeys("Pun");

        // Open calendar
        WebElement calendar = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='undefined-calendar-picker']")));

        calendar.findElement(
                By.xpath(".//*[local-name()='svg' and @data-testid='svg-img']"))
                .click();

        // Calculate future date
        LocalDate futureDate = LocalDate.now().plusMonths(2);
        String month = futureDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = futureDate.getYear();
        int day = 9;

        // Select month
        WebElement monthElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@data-testid='undefined-month-" + month + "-" + year + "']")));

        // Select date
        WebElement dateElement = monthElement.findElement(
                By.xpath(".//div[text()='" + day + "']"));
        dateElement.click();

        // ✅ Assertion – verify date is selected
        String selectedDate = dateElement.getAttribute("aria-selected");
        Assert.assertEquals(selectedDate, "true", "Date was not selected successfully");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}