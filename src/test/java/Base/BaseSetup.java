package Base;

import org.apache.commons.lang3.function.FailableIntToLongFunction;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseSetup {
    private WebDriver driver;
    static String driverPath = "src/drivers";

    public WebDriver getDriver() {
        return driver;
    }

    //Select browser to run test suite. Run this method before run this class (BeforeClass)
    private void setDriver(String browserType, String appURL) {
        if (browserType.equals("chrome") ) {
            driver = initChromeDriver(appURL);
        } else initFirefoxDriver(appURL);
    }

    // Init Browser follow type [Chrome | Firefox | MS Edge]
    /**Chrome Driver**/
    private static WebDriver initChromeDriver(String appURL) {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        options.addArguments("--remote-allow-origins=*");
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        return driver;
    }

    /**Firefox Driver**/
    private static WebDriver initFirefoxDriver(String appURL) {
        System.setProperty("webdriver.gecko.driver", driverPath + "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }
    /**MS Edge Driver**/
    private static WebDriver initMSEdgeDriver(String appURL) {
        System.setProperty("webdriver.edge.driver", driverPath + "msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    // Firstly, run initializeTestBaseSetup method
    @Parameters({ "browserType", "appURL" })

    @BeforeClass
    public void initializeTestBaseSetup (@Optional String browserType, @Optional String appURL) {
        try {
            System.out.println("browserType: "+ browserType);
            System.out.println("appURL: "+ appURL);
            System.out.println( browserType + " is launching...");
            setDriver(browserType, appURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        /**
         * Capture screenshot when test failed only
         */
        String testName = result.getMethod().getMethodName();

        if(!result.isSuccess()) {
            System.out.printf("Test : %s is FAIL\n",testName);
            testName = testName +"-"+System.currentTimeMillis();
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(String.format("./target/screen-shots/%s.png",testName)));
        }else {
            System.out.printf("Test : %s is PASS\n",testName);
        }
    }
}
