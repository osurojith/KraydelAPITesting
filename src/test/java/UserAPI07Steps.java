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

public class UserAPI07Steps extends BaseClass {


    @Step("User enter View Elders Assigned API </api/><version></user/elders>")
    public void User_enter_View_Elders_Assigned_API(String part1, String part2, String part3) {
        this.api = System.getenv("URI") + part1 + part2 + part3;
        System.out.println("API: " + api);
    }

    @Step("User call the View Elders Assigned API")
    public void User_call_the_View_Elders_Assigned_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("User gets data from kraydel database View Elders Assigned API <username>")
    public void get_db_data(String username) throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select person.id as id,person.gender as gender , person.last_name as lname , person.first_name as fname,grampa.location_id as locationid from main.person join main.grampa on grampa.id=person.id join main.grampa_user on grampa_user.grampa_id=person.id and grampa_user.user_id=(select id from main.user where main.user.username='" + username + "')";
            System.out.println(sql);
            results = DBConn.getDBData(sql);
            Assert.assertEquals("No Elder found for given carer.", true, results.next());
            results.previous();
        }
    }


    @Step("Validate Content View Elders Assigned API")
    public void Validate_content() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No elders found", true, jsonPath.getList("content.elders").size() >= 1);
            while (results.next()) {
                count++;
                for (int i = 1; i <= jsonPath.getList("content.elders").size(); i++) {

                    String val = Integer.toString(i - 1);
                    String id = jsonPath.getString("content.elders[" + val + "].id");
                    String lname = jsonPath.getString("content.elders[" + val + "].lastName");
                    String fname = jsonPath.getString("content.elders[" + val + "].firstName");
                    String gender = jsonPath.getString("content.elders[" + val + "].gender");
                    if (EncryptionServiceImpl.decryptToLong(id).toString().equalsIgnoreCase(results.getString("id"))) {

                        Assert.assertEquals("Validate person.last_name", results.getString("lname"), lname);
                        Assert.assertEquals("Validate person.first_name", results.getString("fname"), fname);
                        Assert.assertEquals("Validate person.gender", results.getString("gender"), gender);
                        Assert.assertEquals("Validate person.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    }
                }
            }
            Assert.assertEquals("Data miss match API:DB", jsonPath.getList("content.elders").size(), count);

        }
    }


}
