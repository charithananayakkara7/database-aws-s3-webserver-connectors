package TestBase;

import com.base.page.BaseUnitPageLayer;
import com.base.page.UnitPageLayer;
import common.BrowserSetup;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.testng.Assert.assertEquals;
public class BaseTest extends ApiLayer01 {
    public WebDriver driver;
    public UnitPageLayer unitPageLayer;
    public Properties prop;
    public static final int TIME_OUT = 25;
    public void initialize_browser() {
        prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("src/main/resources/data.properties");
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fis);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        String browserName = prop.getProperty("browser");
        String waitTimestring = prop.getProperty("TIME_OUT");
        int TIME_OUT = Integer.parseInt(waitTimestring);
        driver = BrowserSetup.setBrowser(browserName);
        unitPageLayer = new BaseUnitPageLayer(driver,TIME_OUT);
        System.out.println("Using " + browserName + " browser");
        String url = prop.getProperty("url");
        driver.get(url);
        driver.manage().window().maximize();

    }

    public void browserclose() {
        try {
            driver.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
