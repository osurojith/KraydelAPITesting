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

public class UserAPI10Steps extends BaseClass {
    @Step("User enter User API view unassigned carer <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></users/elders/><elderid></unassigned>")
    public void enter_api(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        api = arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3) + arg4;
    }

    @Step("User call the User API view unassigned carer")
    public void call_api() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
        System.out.println(jsonPath.getList("content.carers").size());
    }

    @Step("Get data from database and Validate User API view unassigned carer <elderid>")
    public void get_data_db(String elderid) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            String sql = null;
            sql="select user_id from main.grampa_user where grampa_id="+elderid+"";
            results = DBConn.getDBData(sql);

            if(results.next())
                sql = "select * from main.person join main.user_role on main.user_role.role_id=2 and user_role.user_id=person.id and person.id <> "+results.getString("user_id")+"";
            else
                sql = "select * from main.person join main.user_role on main.user_role.role_id=2 and user_role.user_id=person.id";

            System.out.println(sql);
            results = DBConn.getDBData(sql);

           Assert.assertEquals("No cares found in the DB",true,results.next());
           results.previous();
        }
    }

    @Step("Validate carer Content view unassigned carer")
    public void validate_contetnt() throws SQLException {
        if (status_code.equals("20000")) {
            int count=0;
            Assert.assertEquals("No carers found",true,jsonPath.getList("content.carers").size()>=1);

            while (results.next()) {
                System.out.println("xxxxxxxxxxx");
                count++;
                for (int i = 1; i <= jsonPath.getList("content.carers").size(); i++) {

                    String id = jsonPath.getString("content.carers[" + (i - 1) + "].id");
                    String fname = jsonPath.getString("content.carers[" + (i - 1) + "].firstName");
                    String lname = jsonPath.getString("content.carers[" + (i - 1) + "].lastName");
                    String email = jsonPath.getString("content.carers[" + (i - 1) + "].email");
                    String picture = jsonPath.getString("content.carers[" + (i - 1) + "].picture");


                    if (EncryptionServiceImpl.decryptToLong(id).toString().equalsIgnoreCase(results.getString("id"))) {

                        System.out.println(fname);
                        Assert.assertEquals("Validate content.carers.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                        Assert.assertEquals("Validate content.carers.fname", results.getString("first_name"), fname);
                        Assert.assertEquals("Validate content.carers.lname", results.getString("last_name"), lname);
                        Assert.assertEquals("Validate content.carers.email", results.getString("email"), email);
                    }
                }
            }
            Assert.assertEquals("Data miss match API:DB",jsonPath.getList("content.carers").size(),count);
        }
    }
}
