package pageObjects.web;

import com.base.page.BaseUnitPageLayer;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
public  class LoginPage extends BaseUnitPageLayer {
    private String emailpth = "#email";
    private String passwordpth= ".user-password input";
    private String submitBtnpth= ".signin-button span";
    private String contactBtn="a[title='Contact Us']";
    private String womentab= "a[title='Women']";
    private String cntemailbox= "#email";

    public LoginPage(WebDriver driver, int TIME_OUT) {
        super(driver,TIME_OUT);
    }

    public void clickSubmitBtnn() throws Exception {
        By btnVal= getLocator(submitBtnpth, BY_TYPE.BY_CSSSELECTOR);
        System.out.println(getText(getLocator(emailpth, BY_TYPE.BY_CSSSELECTOR)));
        click(btnVal,11);
    }

    public void clickSearchBtnn(String email) throws Exception {
        By btnVal1= getLocator(contactBtn, BY_TYPE.BY_CSSSELECTOR);
        By btnVal2=getLocator(womentab,BY_TYPE.BY_CSSSELECTOR);
        By ctemailbox=getLocator(cntemailbox,BY_TYPE.BY_CSSSELECTOR);
        click(btnVal1,10);
        type(ctemailbox,email);
        WaitElementPresent(btnVal2);
    }



}