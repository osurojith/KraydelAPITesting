import KraydelEncryption.EncryptionServiceImpl;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class AlertAPI03 extends BaseClass {


    @Step("User Enter Alert API POST <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></alerts>")
    public void Enter_API(String part1, String part2, String part3) {
        this.api = part1 + part2 + part3;
    }

    @Step("User enter Alert Details Alert API POST <alertMessage> <alertSubject> <subEventType> <grampaId>")
    public void Enter_Alert_details(String alertMessage, String alertSubject, String subEventType, long grampaId) throws Exception {
        body = "{\n" +
                "\"alertMessage\": \"" + alertMessage + "\",\n" +
                "\"alertSubject\": \"" + alertSubject + "\",\n" +
                "\"subEventType\": \"" + subEventType + "\",\n" +
                "\"grampaId\": " + EncryptionServiceImpl.encryptToString(grampaId) + "\n" +
                "}";
        System.out.println(body);
    }

    @Step("User Call Alert API POST")
    public void Call_the_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.postMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


}
