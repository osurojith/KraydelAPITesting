import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class BaseStationAPI03 extends BaseClass {


    @Step("User enter Update Base Station API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></users/><id></status>")
    public void User_enter_Update_Status_API(String part1, String part2, String part3, String part4, String part5) {
        this.api = part1 + part2 + part3 + part4 + part5;
    }

    @Step("Update Base Station API Body <Userstatus>")
    public void Update_body(String status) {
        body = "{\n" +
                " \"status\":\"" + status + "\"\n" +
                "}";

    }

    @Step("User call the Update Base Station API")
    public void User_call_the_Update_status_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

}
