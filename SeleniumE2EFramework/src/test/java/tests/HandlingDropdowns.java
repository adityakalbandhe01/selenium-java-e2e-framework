package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class HandlingDropdowns {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://testautomationpractice.blogspot.com/");

        // Locate dropdown
        WebElement countryDropdown = driver.findElement(By.id("country"));

        // Create Select object
        Select select = new Select(countryDropdown);

        // Select by visible text
        select.selectByVisibleText("India");

        // OR select by value
        // select.selectByValue("india");

        // OR select by index
        // select.selectByIndex(1);

        // Print all options
        System.out.println("Dropdown options:");
        for (WebElement option : select.getOptions()) {
            System.out.println(option.getText());
        }

        // Get selected option
        System.out.println("Selected option: "
                + select.getFirstSelectedOption().getText());

        driver.quit();
    }
}