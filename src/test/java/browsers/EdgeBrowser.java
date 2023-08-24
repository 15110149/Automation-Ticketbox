package browsers;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class EdgeBrowser {
    @Test
    void navigateToEdge(){
        // setup WebDriver Microsoft Edge path
        System.setProperty("webdriver.edge.driver", "src/drivers/msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver();
        driver.navigate().to("https://tiki.vn/");
        // quit browser
        driver.quit();
    }

}
