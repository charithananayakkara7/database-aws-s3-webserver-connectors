package pageObjects.web;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.page.BasePageLayer;

public  class LoginPage extends BasePageLayer {
    private String emailpth = "#email";
    private String passwordpth= ".user-password input";
    private String submitBtnpth= ".signin-button span";
    private String contactBtn="a[title='Contact Us']";
    private String womentab= "a[title='Women']";
    private String cntemailbox= "#email";
    private String logintnpth= ".login";
    private String emailField= "#email";
    private String passFiled= "#passwd";
    private String loginBtn= "button[id='SubmitLogin']";
    private String logoutBtn= ".logout";

    public LoginPage(WebDriver driver, int TIME_OUT) {
        super(driver,TIME_OUT);
    }

    public void clickloginBtn() throws Exception {
        By btnVal= getLocator(logintnpth, BY_TYPE.BY_CSSSELECTOR);
        click(btnVal,11);
    }

    public void doLogin() throws Exception {
        By userName= getLocator(emailField, BY_TYPE.BY_CSSSELECTOR);
        By passWord=getLocator(passFiled,BY_TYPE.BY_CSSSELECTOR);
        By loginsubmitBtn=getLocator( loginBtn,BY_TYPE.BY_CSSSELECTOR);
        By signOutBtn=getLocator(logoutBtn,BY_TYPE.BY_CSSSELECTOR);
        //ToDo change below values to take from json file
        type( userName,"charithananayakkara7@gmail.com");
        type( passWord,"Test1234%");
        click(loginsubmitBtn,10);
        WaitElementPresent(signOutBtn);
    }



}
