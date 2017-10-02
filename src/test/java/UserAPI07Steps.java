
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
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database View Elders Assigned API <username>")
    public void get_db_data(String username) throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select person.id as id,person.gender as gender , person.last_name as lname , person.first_name as fname,grampa.location_id as locationid from main.person join main.grampa on grampa.id=person.id join main.grampa_user on grampa_user.grampa_id=person.id and grampa_user.user_id=(select id from main.user where main.user.username='" + username + "')";
            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));
            Assert.assertEquals("No Elder found for given carer.", true, getResults().next());
            getResults().previous();
        }
    }


    @Step("Validate Content View Elders Assigned API")
    public void Validate_content() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No elders found", true, getJsonPath().getList("content.elders").size() >= 1);
            while (getResults().next()) {
                count++;
                for (int i = 1; i <= getJsonPath().getList("content.elders").size(); i++) {

                    String val = Integer.toString(i - 1);
                    String id = getJsonPath().getString("content.elders[" + val + "].id");
                    String lname = getJsonPath().getString("content.elders[" + val + "].lastName");
                    String fname = getJsonPath().getString("content.elders[" + val + "].firstName");
                    String gender = getJsonPath().getString("content.elders[" + val + "].gender");
                    if (EncryptionServiceImpl.decryptToLong(id).toString().equalsIgnoreCase(getResults().getString("id"))) {

                        Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), lname);
                        Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), fname);
                        Assert.assertEquals("Validate person.gender", getResults().getString("gender"), gender);
                        Assert.assertEquals("Validate person.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    }
                }
            }
            Assert.assertEquals("Data miss match API:DB", getJsonPath().getList("content.elders").size(), count);

        }
    }


}
