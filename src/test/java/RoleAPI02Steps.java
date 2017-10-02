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

public class RoleAPI02Steps extends BaseClass {

    @Step("User enter Role API Get Elder Roles </api/><version></roles/elder-roles/partial>")
    public void Enter_role_API(String part1, String version, String part2) {

        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }

    @Step("User call the Role API Get Elder Roles")
    public void User_call_Role_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from main.grampa_role where id=" + EncryptionServiceImpl.decryptToLong(id) + "";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.grampa_roles. ID:" + EncryptionServiceImpl.decryptToLong(id), true, results.next());
        results.previous();
    }

    @Step("Validate Content Get Elder Roles")
    public void Validate_Content() throws SQLException, ClassNotFoundException {

        for (int i = 1; i <= jsonPath.getList("content.elderRoles").size(); i++) {
            int count = 0;
            String val = Integer.toString(i - 1);
            String roleid = jsonPath.getString("content.elderRoles[" + val + "].id");
            String rolename = jsonPath.getString("content.elderRoles[" + val + "].roleName");


            get_db_data(roleid);
            while (results.next()) {
                count++;
                Assert.assertEquals("Validate elderRoles.id", results.getString("role_id"), EncryptionServiceImpl.decryptToLong(roleid).toString());
                Assert.assertEquals("Validate elderRoles.role_name", results.getString("role_name"), rolename);

            }

            Assert.assertEquals("Data miss match API:DB", 1, count);

        }
    }

}
