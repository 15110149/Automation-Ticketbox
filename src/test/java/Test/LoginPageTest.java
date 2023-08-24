package Test;

import Base.BaseSetup;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseSetup{
    private WebDriver driver ;
    protected WebDriverWait wait;
    protected String URL = "https://ticketbox.vn/";
    public LoginPage loginPage;


    @BeforeClass
    public void setUp() {
    driver = getDriver();
    }


    @Test
    public void LoginSuccessfully() throws InterruptedException {
        System.out.println(driver);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.Login("vanph313@gmail.com","TB123456");

        driver.quit();
    }



}
