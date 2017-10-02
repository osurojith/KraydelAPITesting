
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

public class RoleAPISteps extends BaseClass {


    @Step("User enter Role API Get User Roles </api/><version></roles/partial>")
    public void Enter_role_API(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }

    @Step("User call the Role API Get User Roles")
    public void User_call_Role_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from main.role where id=" + EncryptionServiceImpl.decryptToLong(id) + "";
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
        Assert.assertEquals("No record found  main.role. ID:" + EncryptionServiceImpl.decryptToLong(id), true, getResults().next());
        getResults().previous();
    }

    @Step("Validate Content Get User Roles")
    public void Validate_Content() throws SQLException, ClassNotFoundException {

        for (int i = 1; i <= getJsonPath().getList("content.roles").size(); i++) {
            int count = 0;
            String val = Integer.toString(i - 1);
            String roleid = getJsonPath().getString("content.roles[" + val + "].id");
            String rolename = getJsonPath().getString("content.roles[" + val + "].roleName");


            get_db_data(roleid);
            while (getResults().next()) {
                count++;
                Assert.assertEquals("Validate role.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(roleid).toString());
                Assert.assertEquals("Validate role.role_name", getResults().getString("role_name"), rolename);

            }

            Assert.assertEquals("Data miss match API:DB", 1, count);

        }
    }


}
