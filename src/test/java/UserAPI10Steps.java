
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

public class UserAPI10Steps extends BaseClass {
    @Step("User enter User API view unassigned carer </api/><version></users/elders/><elderid></unassigned>")
    public void enter_api(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        api = System.getenv("URI") + arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3) + arg4;
        System.out.println("API: " + api);
    }

    @Step("User call the User API view unassigned carer")
    public void call_api() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));

    }

    @Step("Get data from database and Validate User API view unassigned carer <elderid>")
    public void get_data_db(String elderid) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            String sql = null;
            sql = "select user_id from main.grampa_user where grampa_id=" + elderid + "";
            setResults(DatabaseFactory.getDBData(sql));

            if (getResults().next())
                sql = "select * from main.person join main.user_role on main.user_role.role_id=2 and user_role.user_id=person.id and person.id <> " + getResults().getString("user_id") + "";
            else
                sql = "select * from main.person join main.user_role on main.user_role.role_id=2 and user_role.user_id=person.id";

            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));

            Assert.assertEquals("No cares found in the DB", true, getResults().next());
            getResults().previous();
        }
    }

    @Step("Validate carer Content view unassigned carer")
    public void validate_contetnt() throws SQLException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No carers found", true, getJsonPath().getList("content.carers").size() >= 1);

            while (getResults().next()) {
                count++;
                for (int i = 1; i <= getJsonPath().getList("content.carers").size(); i++) {

                    String id = getJsonPath().getString("content.carers[" + (i - 1) + "].id");
                    String fname = getJsonPath().getString("content.carers[" + (i - 1) + "].firstName");
                    String lname = getJsonPath().getString("content.carers[" + (i - 1) + "].lastName");
                    String email = getJsonPath().getString("content.carers[" + (i - 1) + "].email");
                    String picture = getJsonPath().getString("content.carers[" + (i - 1) + "].picture");


                    if (EncryptionServiceImpl.decryptToLong(id).toString().equalsIgnoreCase(getResults().getString("id"))) {
                        Assert.assertEquals("Validate content.carers.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                        Assert.assertEquals("Validate content.carers.fname", getResults().getString("first_name"), fname);
                        Assert.assertEquals("Validate content.carers.lname", getResults().getString("last_name"), lname);
                        Assert.assertEquals("Validate content.carers.email", getResults().getString("email"), email);
                    }
                }
            }
            Assert.assertEquals("Data miss match API:DB", getJsonPath().getList("content.carers").size(), count);
        }
    }
}
