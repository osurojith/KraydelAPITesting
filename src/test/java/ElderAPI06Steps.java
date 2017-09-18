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

public class ElderAPI06Steps extends BaseClass {


    @Step("User Enter Assign-carer API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elderid></assign-carer>")
    public void Call_API(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        this.api = arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3) + arg4;
    }

    @Step("User enter Elder Details Assign-carer API <userID> <userRoleID>")
    public void Enter_details(long userID, long userRoleID) throws Exception {
        body = "{\n" +
                " \"user\": {\"id\": \"" + EncryptionServiceImpl.encryptToString(userID) + "\"},\n" +
                "    \"grampaRole\": {\"id\": \"" + EncryptionServiceImpl.encryptToString(userRoleID) + "\"}\n" +
                "}";
    }

    @Step("User Call Assign-carer API")
    public void Call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    @Step("User gets data from kraydel database Assign-carer API <userID> <elderid> <userRoleID>")
    public void get_db_data(String userID, String elderid, String userRoleID) throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select count(*) as status from main.grampa_user where grampa_id=" + (elderid) + " and user_id=" + (userID) + " and grampa_role_id=" + (userRoleID) + "";

            System.out.println(sql);
            results = DBConn.getDBData(sql);

        }
    }

    @Step("Validate back end Assign-carer API <userID> <elderid> <userRoleID>")
    public void Validate_backend(String userID, String elderid, String userRoleID) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            while (results.next()) {
                Assert.assertEquals("Validate elder_user", results.getString("status"), "1");
            }
        }
    }
}
