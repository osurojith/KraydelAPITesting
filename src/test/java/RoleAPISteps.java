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

public class RoleAPISteps extends BaseClass {


    @Step("User enter Role API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></roles/partial>")
    public void Enter_role_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Role API")
    public void User_call_Role_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Content")
    public void Validate_Content() throws SQLException, ClassNotFoundException {

            for (int i = 1; i <= jsonPath.getList("content.roles").size(); i++) {
                String val = Integer.toString(i - 1);
                String roleid=jsonPath.getString("content.roles[" + val + "].id");
                String rolename=jsonPath.getString("content.roles[" + val + "].roleName");

                Assert.assertEquals(true, !roleid.isEmpty());
                Assert.assertEquals(true, !rolename.isEmpty());

                String sqlrole="select * from main.role where id="+ EncryptionServiceImpl.decryptToLong(roleid)+" and role_name='"+rolename+"'";
                Assert.assertEquals("Validate Role id and name :"+sqlrole,1, DBConn.getRowCount(sqlrole));


        }
    }




}
