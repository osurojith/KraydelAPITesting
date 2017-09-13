import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class BaseStationAPI02 extends BaseClass {


    @Step("User enter Search Base-Station API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></base-stations/search>")
    public void get_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Search Base-Station API")
    public void User_call_Base_Station_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("Validate content Search Base-Station API")
    public void Validate_contetnt() {

        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.base-stations").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].id").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].deviceKey").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].status").isEmpty());
                if (jsonPath.getString("content.base-stations[" + val + "].status").equalsIgnoreCase("ASSIGNED")) {
                    for (int x = 1; x <= jsonPath.getList("content.base-stations[" + val + "].grampas").size(); x++) {
                        String val2 = Integer.toString(x - 1);
                        // System.out.println(jsonPath.getString("content.base-stations[" + val + "].grampas["+val2+"].id"));
                        Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].id").isEmpty());
                        Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].firstName").isEmpty());
                        Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].lastName").isEmpty());
                        Assert.assertEquals(true, !jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].location").isEmpty());
                    }

                }
            }
        }
    }

    @Step("Validate Search Base-Station API Pagination")
    public void Validate_Search_API_Pagination() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageNumber").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageSize").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalPages").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalRecords").isEmpty());
        }
    }
}
