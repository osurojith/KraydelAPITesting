import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class EthnicityAPISteps extends BaseClass {


    @Step("User enter Ethnicity API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></ethnicities/partial>")
    public void implementation1(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }


    @Step("User call the Ethnicity API")
    public void User_call_the_Ethnicity_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Ethnicity Content <contentEthnicity>")
    public void Validate_Ethnicity_Content(String status) {
        if (status_code.equals("20000")) {
            Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content").isEmpty());
        }
    }

    @Step("Validate Ethnicity Data Id <EthnicityDataid>")
    public void Validate_Ethnicity_Data_Id(String status) {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.ethnicities").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content.ethnicities[" + val + "].id").isEmpty());
            }
        }
    }

    @Step("Validate Ethnicity Data Name <EthnicityDataName>")
    public void Validate_Ethnicity_Data_Name(String status) {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.ethnicities").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content.ethnicities[" + val + "].name").isEmpty());
            }
        }
    }


}
