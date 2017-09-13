import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class ElderAPI08Steps extends BaseClass {


    @Step("User Enter View unassigned API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/unassigned/partial>")
    public void Enter_API(String arg0, String arg1, String arg2) {
        this.api = arg0 + arg1 + arg2;
    }

    @Step("User Call View unassigned API")
    public void call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("Validate Status Code View unassigned API <status_code>")
    public void Validate_status_code(String status_code) {
        this.status_code = status_code;
        Assert.assertEquals(status_code, jsonPath.getString("statusCode"));
    }

    @Step("Validate Content View unassigned API")
    public void Validate_content() {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.elders").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].id").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].firstName").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].lastName").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].location.id").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].location.name").isEmpty());
            }
        }
    }

}
