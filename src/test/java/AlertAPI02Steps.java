import KraydelEncryption.EncryptionServiceImpl;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import utils.BaseClass;
import utils.DBConn;
import utils.HttpMethods;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AlertAPI02Steps extends BaseClass {
    @Step("User Enter Alert Update Status API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></alerts/><sentalertid></user-alerts/status>")
    public void call_api(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        this.api=arg0+arg1+arg2+ EncryptionServiceImpl.encryptToString(arg3)+arg4;
    }

    @Step("Update request body Alert Update Status API <status>")
    public void set_body(String status) {
        body="{\n" +
                " \"alertStatus\": \""+status+"\"\n" +
                "}";
        System.out.println("Body: "+body);
    }

    @Step("User Call Alert Update Status API")
    public void call_api() {
        System.out.println(body);
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate back end Assign-carer API <sentalertid> <statusVal>")
    public void validate_backend(String sentalertid, String status) throws SQLException, ClassNotFoundException {
        String sql="select * from main.user_alert_details where id="+ sentalertid+" and status="+status+"";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.user_alert_details.", true, results.next());
        results.previous();
    }
}
