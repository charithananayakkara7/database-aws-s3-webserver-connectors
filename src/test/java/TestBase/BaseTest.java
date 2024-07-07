package TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import com.base.page.BasePageLayer;
import com.base.page.PageLayer;

import common.BrowserSetup;
public class BaseTest {
    public WebDriver driver;
    public PageLayer PageLayer;
    public Properties prop;
    public static final int TIME_OUT = 25;

    public void initializeBrowser() {
        Properties prop = new Properties();
        
        try (FileInputStream fis = new FileInputStream("src/main/resources/data.properties")) {
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return; // Exit the method if properties file is not found
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit the method if there is an error loading properties
        }
        
        String browserName = prop.getProperty("browser");
        if (browserName == null) {
            throw new IllegalArgumentException("Browser name not specified in properties file");
        }
        
        String waitTimeString = prop.getProperty("TIME_OUT");
        if (waitTimeString == null) {
            throw new IllegalArgumentException("Timeout value not specified in properties file");
        }
        
        int TIME_OUT;
        try {
            TIME_OUT = Integer.parseInt(waitTimeString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid timeout value specified in properties file");
        }
        
        driver = BrowserSetup.setBrowser(browserName);
        if (driver == null) {
            throw new RuntimeException("Failed to initialize the browser");
        }
        
        PageLayer = new BasePageLayer(driver, TIME_OUT);
        System.out.println("Using " + browserName + " browser");
        
        String url = prop.getProperty("url");
        if (url == null) {
            throw new IllegalArgumentException("URL not specified in properties file");
        }
        
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
