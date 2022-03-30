package stepdef.api;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        Location stf = new Location();
        if (Location.place_id == null) {
            stf.add_json_payload_for_add_place_post_request_with("kevin,english1");
            stf.user_calls_with_http_request("AddplaceAPI", "post");
            stf.api_call_is_sucess_with_status_code(200);
            stf.verifyPlaceIdMapsToUsing("kevin,english1","GetPlaceAPI");
        }
    }
}
