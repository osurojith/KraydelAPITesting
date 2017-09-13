import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class HealthIssueSteps {
    public String api = null;
    public String token = null;
    Response response;
    JsonPath jsonPath;
    String status_code;

    @Step("User enter HealthIssue API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></healthissues/partial>")
    public void User_enter_HealthIssue_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the HealthIssue API")
    public void User_call_the_HealthIssue_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate HealthIssue Content")
    public void Validate_HealthIssue_Content() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !jsonPath.getString("content").isEmpty());
        }
    }

    @Step("Validate HealthIssue Data Id")
    public void Validate_HealthIssue_Data_Id() {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.health-issues").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.health-issues[" + val + "].id").isEmpty());
            }
        }
    }

    @Step("Validate HealthIssue Data Issue")
    public void Validate_HealthIssue_Data_Issue() {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.health-issues").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.health-issues[" + val + "].issue").isEmpty());
            }
        }
    }


}
