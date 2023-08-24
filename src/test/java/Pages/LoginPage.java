package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    protected WebDriver Driver ;
    //Constructor that will be automatically called as soon as the object of the class is created
    public LoginPage(WebDriver driver){
        this.Driver = driver;
    }

    /**locator**/
    By loginBtn = By.cssSelector(".biuDgp");
    By loginByEmail_icon = By.cssSelector("img[alt='login email']");
    By emailInput = By.cssSelector("input[name='email']");
    By passwordInput = By.cssSelector("input[name='password']");
    By submitLogin = By.cssSelector("button[type='primary']");


    public void setPasswordInput(String password){
        this.passwordInput = passwordInput;
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        Driver.findElement(passwordInput).sendKeys(password);
    }
    public void setEmailInput(String text) {
        this.emailInput = emailInput;
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        Driver.findElement(emailInput).sendKeys(text);
    }

    public void clickLoginBtn() {
        this.loginBtn = loginBtn;
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginBtn));
        Driver.findElement(loginBtn).click();
    }
    public void clickLoginByEmail_icon() {
        this.loginByEmail_icon = loginByEmail_icon;
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginByEmail_icon));
        Driver.findElement(loginByEmail_icon).click();
    }
    public void clickSubmit_btn() {
        this.submitLogin = submitLogin;
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitLogin));
        Driver.findElement(submitLogin).click();
    }

    /**Methods**/
    public void Login(String email_input, String password_input) {
        // Step1: click Login button on header
        clickLoginBtn();
        // Step2: click Login by email (email icon)
        clickLoginByEmail_icon();
        // Step 3: Input Email
        setEmailInput(email_input);
        // Step 4: Input Password
        setPasswordInput(password_input);
        // Step 5: Click Submit to login
        clickSubmit_btn();

    }
}
