package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void init() {
        setUp();
    }

    @Test
    public void verifyLogin() {
        LoginPage lp = new LoginPage(driver);
        lp.login("Admin", "admin123");
    }

    @AfterMethod
    public void close() {
        tearDown();
    }
}