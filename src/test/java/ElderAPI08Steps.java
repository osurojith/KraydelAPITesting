import KraydelEncryption.EncryptionServiceImpl;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.DBConn;
import utils.HttpMethods;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ElderAPI08Steps extends BaseClass {


    @Step("User Enter View Assigned API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/unassigned/partial>")
    public void Enter_API(String arg0, String arg1, String arg2) {
        this.api = arg0 + arg1 + arg2;
    }

    @Step("User Call View Assigned API")
    public void call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("Validate Status Code View Assigned API <status_code>")
    public void Validate_status_code(String status_code) {
        this.status_code = status_code;
        Assert.assertEquals(status_code, jsonPath.getString("statusCode"));
    }

    @Step("Validate Content View Assigned API")
    public void Validate_content() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.elders").size(); i++) {
                String val = Integer.toString(i - 1);
                String id=jsonPath.getString("content.elders[" + val + "].id");
                String lname=jsonPath.getString("content.elders[" + val + "].lastName");
                String fname=jsonPath.getString("content.elders[" + val + "].firstName");
                String locationid=jsonPath.getString("content.elders[" + val + "].location.id");


                Assert.assertEquals(true, !id.isEmpty());
                Assert.assertEquals(true, !fname.isEmpty());
                Assert.assertEquals(true, !lname.isEmpty());
                Assert.assertEquals(true, !locationid.isEmpty());


                String sqlusergrampa="select * from main.grampa where id="+ EncryptionServiceImpl.decryptToLong(id)+" and base_station_id IS NULL";
                Assert.assertEquals("Validate grampa_user table "+sqlusergrampa,1, DBConn.getRowCount(sqlusergrampa));

            }
        }
    }

}
