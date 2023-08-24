package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class FirefoxBrowser {
    /**
     * Todo: open Firefox browser then navigate to google.com.vn
     */
    @Test
    void navigateToFirefox() {
        // setup WebDriver Firefox path
        System.setProperty("webdriver.gecko.driver", "src/drivers/geckodriver.exe");
        // launch Firefox
        WebDriver driver = new FirefoxDriver();
        // access to website
        driver.get("https://tiki.vn/");
        // quit browser
        driver.quit();
    }
}
