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

public class ElderAPI02Steps extends BaseClass {

    @Step("User enter Elder Search API view carers <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elder-ID></carers>")
    public void Enter_API(String arg0, String arg1, String arg2, String arg3, String arg4) {
        this.api = arg0 + arg1 + arg2 + arg3 + arg4;
    }

    @Step("User call the Elder Search API view carers")
    public void Call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    @Step("User gets data from kraydel database Elder Search API view carers <elder-ID>")
    public void get_data_from_database(String elderid) throws SQLException, ClassNotFoundException {
        String sql = "Select person.id as id, person.first_name AS fname, person.last_name as lname, person.email as email, grampa_user.grampa_role_id as roleId, grampa_role.role_name as rolename, main.user.username as username from main.grampa_user join main.person on grampa_user.user_id=person.id and grampa_user.grampa_id="+EncryptionServiceImpl.decryptToLong(elderid)+" join main.grampa_role on grampa_user.grampa_role_id=grampa_role.role_id join main.user on grampa_user.user_id=main.user.id";
        results=DBConn.getDBData(sql);

    }

    @Step("Validate Elder Search API view carers Content <elder-ID>")
    public void Validate_content(String elderId) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            while (results.next()) {
            for (int i = 1; i <= jsonPath.getList("content.carers").size(); i++) {
                String val = Integer.toString(i - 1);

                String id = jsonPath.getString("content.carers[" + val + "].id");
                String fname = jsonPath.getString("content.carers[" + val + "].firstName");
                String lname = jsonPath.getString("content.carers[" + val + "].lastName");
                String email = jsonPath.getString("content.carers[" + val + "].email");
                String roleid = jsonPath.getString("content.carers[" + val + "].roleID");
                String rolename = jsonPath.getString("content.carers[" + val + "].roleName");
                String username = jsonPath.getString("content.carers[" + val + "].username");

            if(EncryptionServiceImpl.decryptToLong(id).toString().equalsIgnoreCase(results.getString("id"))) {
                Assert.assertEquals("Validate person.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                Assert.assertEquals("Validate person.id", results.getString("fname"), fname);
                Assert.assertEquals("Validate person.id", results.getString("lname"), lname);
                Assert.assertEquals("Validate person.id", results.getString("email"), email);
                Assert.assertEquals("Validate person.id", results.getString("roleid"), roleid);
                Assert.assertEquals("Validate person.id", results.getString("rolename"), rolename);
                Assert.assertEquals("Validate person.id", results.getString("username"), username);
                System.out.println(results.getString("fname")+"    "+fname);
            }

            }
            }
        }
    }


}
