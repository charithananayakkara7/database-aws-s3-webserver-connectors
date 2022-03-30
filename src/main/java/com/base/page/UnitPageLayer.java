package com.base.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

//import static com.base.page.BaseUnitPageLayer.TIME_OUT;

public abstract  class UnitPageLayer {
    WebDriver driver;
    int TIME_OUT;

    public UnitPageLayer(WebDriver driver, int TIME_OUT) {
        this.driver = driver;
        this.TIME_OUT = TIME_OUT;
    }
    public enum BY_TYPE {
        BY_XPATH, BY_LINKTEXT, BY_ID, BY_CLASSNAME, BY_NAME, BY_CSSSELECTOR, BY_PARTIALLINKTEXT, BY_TAGNAME
    }
    public abstract By getLocator(String locator, BaseUnitPageLayer.BY_TYPE type);
    public abstract void click(By locator, int TIME_OUT) throws Exception;
    public abstract void fluentWait(By locator, int interval);
    public abstract void WaitElementClickable(By locator);
    public abstract void WaitElementPresent(By locator);
    public abstract  void WaittextToBePresentInElement(By locator, String value);
    public abstract void  type(By locator, String value);
    public abstract String getText (By locator);
    public abstract void Clear (By locator);
    public abstract  void setTimeOut( long timeoutInSeconds);
    public abstract String getPageTitle ();


}