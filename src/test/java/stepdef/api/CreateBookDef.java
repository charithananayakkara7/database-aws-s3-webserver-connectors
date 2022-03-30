package stepdef.api;

import TestBase.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.ApiresourcesUrl;
import resources.requestpayloads;
import io.restassured.http.ContentType;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class CreateBookDef extends BaseTest {
    ResponseSpecification resspec;
    RequestSpecification res;
    Response response;
    requestpayloads payload = new requestpayloads();
    static String id;

    @Given("Add json payload for create new book post request with {string}")
    public void addJsonPayloadForCreateNewBookPostRequestWithEdition(String name) throws IOException {
        res = given().spec(request_specfications02())
                .body(payload.create_car(name));
    }


    @When("User calls resorce url  {string} with {string} http request")
    public void user_calls_resorce_url_with_http_request(String resourseURL, String HttpMethod) {
        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        ApiresourcesUrl resourceAPI = ApiresourcesUrl.valueOf(resourseURL);
        System.out.println("---Using resource url---- ");
        System.out.println(resourceAPI);
        System.out.println(resourceAPI.getresource());

        if (HttpMethod.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getresource());
        } else if (HttpMethod.equalsIgnoreCase("POST")) {
            response = res.when().post(resourceAPI.getresource());
            System.out.println("here is the responsed selected --" + response);
        } else {
            System.out.println("wrong http method try again or add new http method from step definition file");
        }

    }

    @Then("API response is sucess with status code {int}")
    public void api_response_is_sucess_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), 201);

    }
    @Then("Get product ID from the created product")
    public void Get_product_ID_from_the_created_product() {
        id =getJsonpath(response,"id");

    }

    @Given("verify get new product info using {string} {string}")
    public void verify_get_new_product_info_using(String resourseURL,String Model) throws IOException {
        //res have base URL/end point
        res=given().spec(request_specfications02());
        ApiresourcesUrl resourceAPI = ApiresourcesUrl.valueOf(resourseURL);
        response = res.when().get(resourceAPI.getresource()+id);
        //Get name from reponse from get request
        String actual_name=getJsonpath(response,"modelType");
        Assert.assertEquals(actual_name,Model);
    }

}



