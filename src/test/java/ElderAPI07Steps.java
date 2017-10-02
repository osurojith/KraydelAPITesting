
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

public class ElderAPI07Steps extends BaseClass {


    @Step("User Enter Unassign-carer API </api/><version></elders/><elderid></unassign-carer>")
    public void Call_API(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        this.api =System.getenv("URI")+ arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3) + arg4;
        System.out.println("API: " + api);
    }

    @Step("User enter Elder Details Unassign-carer API <userID> <userRoleID>")
    public void Enter_details(long userID, long userRoleID) throws Exception {
        body = "{\n" +
                " \"user\": {\"id\": \"" + EncryptionServiceImpl.encryptToString(userID) + "\"},\n" +
                "    \"grampaRole\": {\"id\": \"" + EncryptionServiceImpl.encryptToString(userRoleID) + "\"}\n" +
                "}";
        System.out.println("Body: "+body);
    }

    @Step("User Call Unassign-carer API")
    public void Call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.putMethodBody(this.api, header, body);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }
    @Step("User gets data from kraydel database Unassign-carer API <userID> <elderid> <userRoleID>")
    public void get_db_data(String userID, String elderid, String userRoleID) throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select count(*) as status from main.grampa_user where grampa_id=" + (elderid) + " and user_id=" + (userID) + " and grampa_role_id=" + (userRoleID) + "";

            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));
        }
    }

    @Step("Validate back end Unassign-carer API <userID> <elderid> <userRoleID>")
    public void Validate_backend(String userID, String elderid, String userRoleID) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            while (getResults().next()) {
                Assert.assertEquals("Validate elder_user", getResults().getString("status"), "0");
            }
        }

    }

}
