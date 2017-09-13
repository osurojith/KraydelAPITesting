import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class LocationAPISteps extends BaseClass {


    @Step("User enter Location API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></locations/partial>")
    public void User_enter_Location_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Location API")
    public void User_call_the_Location_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Location Content")
    public void Validate_Location_Content() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !jsonPath.getString("content").isEmpty());
        }
    }

    @Step("Validate Location Data Id")
    public void Validate_Location_Data_Id() {
        if (status_code.equals("20000")) {

            for (int i = 1; i <= jsonPath.getList("content.locations").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.locations[" + val + "].id").isEmpty());
            }
        }
    }

    @Step("Validate Location Data Name")
    public void Validate_Location_Data_Name() {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.locations").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.locations[" + val + "].name").isEmpty());
            }
        }
    }


}
