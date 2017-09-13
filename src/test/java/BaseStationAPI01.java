import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class BaseStationAPI01 extends BaseClass {


    @Step("User enter Get Base-Station API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></base-stations/partial>")
    public void get_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Get Base-Station API")
    public void User_call_Base_Station_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate content Get Base-Station API")
    public void Validate_contetnt() {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.base-stations").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].id").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].deviceKey").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].tvBrandId").isEmpty());
            }
        }
    }
}
