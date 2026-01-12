package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseclass.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;

public class AddEmployeeTest extends BaseTest {

    @BeforeMethod
    public void init() {
        setUp();
    }

    @Test
    public void verifyAddEmployee() {

        LoginPage login = new LoginPage(driver);
        login.login("Admin", "admin123");
        
        DashboardPage dashboard = new DashboardPage(driver);
        dashboard.goToPIM();
        
        PIMPage pim = new PIMPage(driver);
        pim.addEmployee("Rahul", "Sharma");
    }

    
    
    
    @AfterMethod
    
    public void tearDownBrowser() {
        tearDown();
    }
}