import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class CountryAPI01Steps extends BaseClass {


    @Step("User enter Country API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></countries/partial>")
    public void User_enter_Country_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Country API")
    public void User_call_the_Country_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Country Content <contentCountry>")
    public void Validate_Country_Content(String status) {
        if (status_code.equals("20000")) {
            Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content").isEmpty());
        }
    }

    @Step("Validate Country Data Id <CountryDataid>")
    public void Validate_Country_Data_Id(String status) {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.countries").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content.countries[" + val + "].id").isEmpty());
            }
        }
    }

    @Step("Validate Country Data Name <CountryDataName>")
    public void Validate_Country_Data_Name(String status) {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.countries").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content.countries[" + val + "].name").isEmpty());
            }
        }
    }

    @Step("Validate City Content <contentCountry>")
    public void Validate_City_Content(String status) {
        if (status_code.equals("20000")) {
            Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content").isEmpty());
        }
    }

    @Step("Validate City Data Id <CountryDataid>")
    public void Validate_City_Data_Id(String status) {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.cities").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content.cities[" + val + "].id").isEmpty());
            }
        }
    }

    @Step("Validate City Data Name <CountryDataName>")
    public void Validate_City_Data_Name(String status) {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.cities").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(status.equals("Yes"), !jsonPath.getString("content.cities[" + val + "].name").isEmpty());
            }
        }
    }

    @Step("User enter Country API2 <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></countries/><country-id></cities/partial>")
    public void User_enter_Country_API2(String part1, String part2, String part3, String part4, String part5) {
        this.api = part1 + part2 + part3 + part4 + part5;
    }
}
