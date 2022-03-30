package cucmber.Options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features =
       "src/test/java/feature files/ui/UserLogin.feature",
        //"src/test/java/feature files/api/book_details_validation.feature",
        //"src/test/java/feature files/api/createbook.feature",

        plugin = {"pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber-report.json"},
 glue = {"stepdef.ui",
         "stepdef.api"
            })
public class TestRunner {
///tags= {"@Addplace , @DeletePlace"}
    //tags = {"@regression"}

}
