package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class CoccocBrowser {
    @Test
    void navigateToCoccoc(){
        // Path to the ChromeDriver.
        System.setProperty("webdriver.chrome.driver","C://Users/Van Nguyen/Downloads/Tiki2022/driver/chromedriver.exe");
        // Path to the Coccoc browser.
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C://Program Files/CocCoc/Browser/Application/browser.exe");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.google.com/");
        // quit browser
        driver.quit();
    }
}
