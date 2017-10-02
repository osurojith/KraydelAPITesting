
import com.aut.BaseClass;
import com.aut.DatabaseFactory;
import com.aut.EncryptionServiceImpl;
import com.aut.HttpMethodsFactory;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserAPI08Steps extends BaseClass {

    @Step("User enter Update Status API </api/><version></users/><id></status>")
    public void User_enter_Update_Status_API(String part1, String part2, String part3, long part4, String part5) throws Exception {
        this.api = System.getenv("URI") + part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4) + part5;
        System.out.println("API: " + api);
    }

    @Step("Update API Body <Userstatus>")
    public void Update_body(String status) {
        body = "{\n" +
                " \"status\":\"" + status + "\"\n" +
                "}";
        System.out.println("Body: " + body);
    }

    @Step("User call the Update Status API")
    public void User_call_the_Update_status_API() {

        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("User gets data from kraydel database Update Status API <id>")
    public void get_db_data(String userid) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select main.user.status from main.user where id=" + userid + "";
            System.out.println(sql);
            results = DatabaseFactory.getDBData(sql);
        }
    }

    @Step("Validate Back End Update Status API <Userstatus> <id>")
    public void Validate_backend(String userstatus, String id) throws Exception {
        if (status_code.equals("20000")) {
            while (results.next()) {
                String status = userstatus.replace("INACTIVE", "3").replace("ACTIVE", "1");
                Assert.assertEquals("Validate user.status", results.getString("status"), status);
            }
        }
    }

}
