package TestBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class ApiLayer02 {
    public  static RequestSpecification req;


    public RequestSpecification request_specfications02() throws IOException {
        if (req== null) {
            PrintStream log = new PrintStream(new FileOutputStream("apilayer02logs.txt"));
            req = new RequestSpecBuilder().setBaseUri(readglobalvalue("BaseUrlQA"))
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON).build();

            return req;
        }
        return req;
    }

    public static String readglobalvalue (String val) throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("src/main/resources/global_var.properties/ApiBaseUrl.properties");
        prop.load(file);
        return prop.getProperty(val);
    }

    public String getJsonpath(Response response, String key){
        String res=response.asString();
        JsonPath js = new JsonPath(res);
        return js.get(key).toString();

    }
}
