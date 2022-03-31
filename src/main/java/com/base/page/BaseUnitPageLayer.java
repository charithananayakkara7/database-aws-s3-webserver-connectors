package com.base.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BaseUnitPageLayer extends UnitPageLayer {
    Logger logger = LogManager.getLogger(BaseUnitPageLayer.class);
    public WebDriver driver;
    public static final int SLEEP_TIME_MILL = 8000;
    int CONFIG_TIME_OUT;
    public BaseUnitPageLayer(WebDriver driver,int TIME_OUT) {
        super(driver,TIME_OUT);
        this.driver = driver;
        this.TIME_OUT = TIME_OUT;
    }

    @Override
    public By getLocator(String locator, BY_TYPE type) {
        switch (type) {
            case BY_XPATH:
                return By.xpath(locator);
            case BY_LINKTEXT:
                return By.linkText(locator);
            case BY_ID:
                return By.id(locator);
            case BY_CSSSELECTOR:
                return By.cssSelector(locator);
            case BY_CLASSNAME:
                return By.className(locator);
            case BY_NAME:
                return By.name(locator);
            case BY_PARTIALLINKTEXT:
                return By.partialLinkText(locator);
            case BY_TAGNAME:
                return By.tagName(locator);

        }
        throw new IllegalArgumentException(
                "Please provide correct locator type");
    }

    @Override
    public void click(By locator,int CONFIG_TIME_OUT) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, CONFIG_TIME_OUT);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    @Override
    public void fluentWait(By locator, int interval) {
// for its presence once every 5 seconds.
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(interval, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }

    @Override
    public void WaitElementClickable(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            setTimeOut(TIME_OUT);
            logger.info("Waited until element visibility "+locator);
        }
        catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void WaitElementPresent(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            setTimeOut(TIME_OUT);
            logger.info("Waited until element visibility "+locator);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void WaittextToBePresentInElement(By locator, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
            setTimeOut(TIME_OUT);
            logger.info("Waited until element visibility "+locator);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void type(By locator, String value) {
        try {

            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            setTimeOut(TIME_OUT);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            element.sendKeys(value);
            logger.info("Waited until element visibility of"+locator+"and send the vaule = "+value);
        } catch (NoSuchElementException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String getText(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            setTimeOut(TIME_OUT);
            return element.getText();
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public void Clear(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            setTimeOut(TIME_OUT);
            element.clear();
            logger.info("Clear the element value "+locator);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    @Override
    public void setTimeOut(long timeoutInSeconds) {
        driver.manage().timeouts().implicitlyWait(timeoutInSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(timeoutInSeconds, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(timeoutInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public String getPageTitle() {
        String title = driver.getCurrentUrl();
        return title;
    }



}