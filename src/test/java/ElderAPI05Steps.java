import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class ElderAPI05Steps extends BaseClass {

    @Step("User Enter Update Elder API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elderid>")
    public void Enter_API(String arg0, String arg1, String arg2, String arg3) {
        this.api = arg0 + arg1 + arg2 + arg3;
    }


    @Step("User enter Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void Enter_user_details(String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) {
        body = "{\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"email\":\"" + email + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"ethnicityId\":\"" + ethnicityId + "\",\n" +
                "\"religionId\":\"" + religionId + "\",\n" +
                "\"dateOfBirth\":\"" + dateOfBirth + "\",\n" +
                "\"locationId\":\"" + locationId + "\",\n" +
                "\"status\":\"" + elderstatus + "\",";
    }


    @Step("User enter List: addresses Update Elder API <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details(String countryId, String addressID, String postalCode, String doorNumber, String street, String cityId, String addressType) {
        body = body + "\"addresses\":[{\"countryId\": \"" + countryId + "\",\"id\": \"" + addressID + "\",\"doorNumber\":\"" + doorNumber + "\",\"street\":\"" + street + "\",\"postalCode\":\"" + postalCode + "\",\"cityId\":\"" + cityId + "\",\"addressType\":\"" + addressType + "\"}],";
    }


    @Step("User enter phoneNumber: Update Elder API <phoneID> <phoneNumber> <phoneType>")
    public void Enter_phoneNumber(String phoneID, String phoneNumber, String phoneType) {
        body = body + "\"phoneNumbers\":[{\"id\": \"" + phoneID + "\",\"phoneNumber\":\"" + phoneNumber + "\",\"phoneType\":\"" + phoneType + "\"}],";
    }

    @Step("User enter baseStation: Update Elder API <baseStationid> <tvBrandId>")
    public void Enter_baseStation(String baseStationid, String tvBrandId) {
        body = body + "\"baseStation\":{\"id\":\"" + baseStationid + "\",\"tvBrandId\":\"" + tvBrandId + "\"}}";
    }

    @Step("User Call Update Elder API")
    public void Call_create_user_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


}
