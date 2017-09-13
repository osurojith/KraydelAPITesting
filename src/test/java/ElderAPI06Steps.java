import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class ElderAPI06Steps extends BaseClass {


    @Step("User Enter Assign-carer API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elderid></assign-carer>")
    public void Call_API(String arg0, String arg1, String arg2, String arg3, String arg4) {
        this.api = arg0 + arg1 + arg2 + arg3 + arg4;
    }

    @Step("User enter Elder Details Assign-carer API <userID> <userRoleID>")
    public void Enter_details(String userID, String userRoleID) {
        body = "{\n" +
                " \"user\": {\"id\": \"" + userID + "\"},\n" +
                "    \"grampaRole\": {\"id\": \"" + userRoleID + "\"}\n" +
                "}";
    }

    @Step("User Call Assign-carer API")
    public void Call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


}
