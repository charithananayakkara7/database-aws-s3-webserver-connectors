package stepdef.ui;
import TestBase.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.web.LoginPage;

public class UserLoginTest extends BaseTest {
    @Given("I initialize the browser and redirection")
    public void InitializeBrowser() {
        initializeBrowser();

    }


    @And("I log in to the appplication")
    public void navigateHomePage() throws Exception {
        LoginPage loginPage = new LoginPage(driver, TIME_OUT);
           loginPage.clickloginBtn();
           loginPage.doLogin();
        }

    @Then("I close the browser")
    public void closeBrowser() throws Exception {
        browserClose();
            }
}

