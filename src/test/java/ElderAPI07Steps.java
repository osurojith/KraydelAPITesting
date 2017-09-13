import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class ElderAPI07Steps extends BaseClass {


    @Step("User Enter Unassign-carer API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elderid></unassign-carer>")
    public void Call_API(String arg0, String arg1, String arg2, String arg3, String arg4) {
        this.api = arg0 + arg1 + arg2 + arg3 + arg4;
    }

    @Step("User enter Elder Details Unassign-carer API <userID> <userRoleID>")
    public void Enter_details(String userID, String userRoleID) {
        body = "{\n" +
                " \"user\": {\"id\": \"" + userID + "\"},\n" +
                "    \"grampaRole\": {\"id\": \"" + userRoleID + "\"}\n" +
                "}";
    }

    @Step("User Call Unassign-carer API")
    public void Call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


}
