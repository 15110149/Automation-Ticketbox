package browsers;
/****/
import org.apache.commons.lang3.function.FailableIntToLongFunction;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ChromeBrowser {
    /**
     * Todo: open chrome browser, navigate & authen
     * 2 cases
     * Login successfully
     * Login with invalid password
     */
    static private WebDriver driver;
    private String accName;
    private String currentTitleTopic;
    private String warningPassword;

    @BeforeMethod
    void setUp() {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    protected WebDriverWait wait;
    protected String URL = "https://vnexpress.net/trong-tre-du-co-ve-lam-to-4545196.html";

    @Test
    void LoginSuccessfully() throws InterruptedException {
        // setup WebDriver Chrome path
        System.setProperty("webdriver.chrome.driver", "src/drivers/chromedriver.exe");

        // Step1: access to website
        navigateToURL();
        windowExpandMaximum();    // Expand window

        // Step2: click Login on header
        clickLoginBtn();

        WebElement frameLogin = driver.findElement(By.xpath("//iframe[@class='mfp-iframe iframe_guest']"));  //Locate the frame on the webPage
        driver.switchTo().frame(frameLogin);          //Switch frame from default frame to Login frame

        // Step3: input Email
        inputEmailLogin("qctiki002@gmail.com");

        // Step4: input Password
        inputPasswordLogin("VN123456");
        // Step5: Click to submit login
        submitLogin();
        Thread.sleep(3000);
        driver.switchTo().defaultContent();

        // Verify successfully login
        verifyAccountName("qctiki002");
        // Verify title of topic
        verifyTitleOfTopic("Trồng tre dụ cò về làm tổ");
        // Verify title of topic
        verifyTitleOfPage("Trồng tre dụ cò về làm tổ - VnExpress");

        driver.quit(); // close all tabs opening
        // driver.close(); // close only current tab index
    }

    @Test
    void LoginFail_InvalidPassword() throws InterruptedException {

        // Step1: access to website
        navigateToURL();
        windowExpandMaximum();    // Expand window

        // Step2: click Login on header
        clickLoginBtn();
        WebElement frameLogin = driver.findElement(By.xpath("//iframe[@class='mfp-iframe iframe_guest']"));  //Locate the frame on the webPage
        driver.switchTo().frame(frameLogin);          //Switch frame from default frame to Login frame

        // Step3: input Email
        inputEmailLogin("qctiki002@gmail.com");

        // Step4: input Password
        inputPasswordLogin("VN9999999");

        // Step5: Click to submit login
        submitLogin();
        Thread.sleep(3000);
        verifyErrorMess_InvalidPassword("Sai mật khẩu lấy lại mật khẩu");

        driver.quit(); // close all tabs opening
    }




    private void navigateToURL() {
        this.driver.get(URL);
    }

    private void verifyTitleOfPage(String titlePage) {
        Assert.assertEquals(titlePage, driver.getTitle());
    }

    private void windowExpandMaximum() {
        this.driver.manage().window().maximize();
    }

    private void clickLoginBtn() {
        driver.findElement(By.xpath("//ul[@class='myvne_form_log']//a")).click();
    }

//    private void countOfFrame() {
//        // count iframe
//        int sizeIFrame = driver.findElements(By.tagName("iframe")).size();
//        System.out.printf("Number of Frames on a Page:" + sizeIFrame);
//    }

    private void inputEmailLogin(String inputEmail) {
        WebElement txtEmail = driver.findElement(By.xpath("//*[@id='myvne_email_input']"));
        txtEmail.sendKeys(inputEmail + "\n");
    }

    private void inputPasswordLogin(String inputPassword) {
        WebElement txtPassword = driver.findElement(By.xpath("//*[@id='myvne_password_input']"));
        txtPassword.sendKeys(inputPassword + "\n");
    }

    private void submitLogin() {
        WebElement btnLogin = driver.findElement(By.xpath("//button[@id='myvne_button_login']"));
        btnLogin.click();
    }

    private void verifyAccountName( String expectAccountName){
        WebElement avatar = driver.findElement(By.xpath("//span[@class='avatar_inner']"));
        avatar.click();
        WebElement userName = driver.findElement(By.xpath("//span[@class='name_sub']"));
        accName = userName.getText();
        System.out.println(userName.getText());
        System.out.println(accName);
        Assert.assertEquals(accName, expectAccountName);

    }

    private void verifyTitleOfTopic(String expectTitleTopic){
        WebElement titleTopic = driver.findElement(By.xpath("//h1[@class='title-detail']"));
        currentTitleTopic = titleTopic.getText();
        Assert.assertEquals(currentTitleTopic, expectTitleTopic);
    }

    private void verifyErrorMess_InvalidPassword(String expectErrorPassword){
        WebElement errorPassword = driver.findElement(By.xpath("//span[@id='error_myvne_password']"));
        warningPassword = errorPassword.getText();
        Assert.assertEquals(warningPassword, expectErrorPassword);
    }

    @AfterMethod
    void tearDown(ITestResult result) throws IOException {
        /**
         * Capture screenshot when test failed only
         */
        String testName = result.getMethod().getMethodName();

        if (!result.isSuccess()) {
            System.out.printf("Test : %s is FAIL\n", testName);
            testName = testName + "-" + System.currentTimeMillis();
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(String.format("./target/screen-shots/%s.png", testName)));
        } else {
            System.out.printf("Test : %s is PASS\n", testName);
        }
    }
}

