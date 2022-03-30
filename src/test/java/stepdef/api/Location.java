package stepdef.api;

import TestBase.ApiLayer02;
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
import TestBase.BaseTest;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class Location extends BaseTest {
    ResponseSpecification resspec;
    RequestSpecification res;
    Response response;
    requestpayloads payload=new requestpayloads();
    static String place_id;

    @Given("Add json payload for add place post request with {string} {string} {string}")

    public void add_json_payload_for_add_place_post_request_with(String name) throws IOException {
        res=given().spec(request_specfications01())
            .body(payload.create_car(name));
    }


    @When("User calls {string} with {string} http request")
    public void user_calls_with_http_request(String resourseURL, String HttpMethod) {
        resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        ApiresourcesUrl resourceAPI= ApiresourcesUrl.valueOf(resourseURL);
        System.out.println("---Using resource url---- ");
        System.out.println(resourceAPI);
        System.out.println(resourceAPI.getresource());

        if(HttpMethod.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceAPI.getresource());
        }
        else if (HttpMethod.equalsIgnoreCase("POST"))
        {
            response = res.when().post(resourceAPI.getresource());
            System.out.println("here is the responsed selected --"+response);
        }
        else
        {
            System.out.println("wrong http method try again or add new http method from step definition file");
        }


    }

    @Then("API call is sucess with status code {int}")
    public void api_call_is_sucess_with_status_code(Integer int1) {
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Then("{string} is {string}")
    public void is (String keyvaule, String expectedpairvaule) {
        Assert.assertEquals(getJsonpath(response,keyvaule),expectedpairvaule);
    }
    @And("verify place id maps to {string} using {string}")
    public void verifyPlaceIdMapsToUsing(String expected_name,String resourseURL) throws IOException {
        //get place response from post request

        place_id =getJsonpath(response,"place_id");
       //res have base URL/end point/placeid
        res=given().spec(request_specfications01().queryParam("place_id",place_id));
        user_calls_with_http_request(resourseURL,"GET");
        //Get name from reponse from get request
        String actual_name=getJsonpath(response,"name");
        Assert.assertEquals(actual_name,expected_name);


    }
    @Given("Vf delete place functionality is working")
    public void vf_delete_place_functionality_is_working() throws IOException {
        System.out.println("place _id copied from get req"+place_id);
        res=given().spec(request_specfications01()).body(payload.delete_car(place_id));
    }

}
