package stepdef.ui;

import TestBase.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import pageObjects.web.LoginPage;
import resources.requestpayloads;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class UserLoginTest extends BaseTest {


    @Given("Initialize the browser and redirection")
    public void initialize_the_browser_with_chrome() {
        initialize_browser();

    }


    @And("Navigate to the Home page {string}")
    public void navigate_to_the_Home_page(String Email) throws Exception {
        LoginPage loginPage = new LoginPage(driver, TIME_OUT);
            loginPage.clickSearchBtnn(Email);
        }



    @And("^close browsers")
    public void close_browsers() throws Throwable {
        browserclose();
    }
}

