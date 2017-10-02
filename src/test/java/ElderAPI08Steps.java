
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

public class ElderAPI08Steps extends BaseClass {


    @Step("User Enter View Assigned API </api/><version></elders/unassigned/partial>")
    public void Enter_API(String arg0, String arg1, String arg2) {
        this.api = System.getenv("URI") + arg0 + arg1 + arg2;
        System.out.println("API: " + api);
    }

    @Step("User Call View Assigned API")
    public void call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database View Assigned API")
    public void get_db_data() throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select person.id as id , person.last_name as lname , person.first_name as fname,grampa.location_id as locationid from main.person join main.grampa on grampa.id=person.id and grampa.base_station_id is null";
            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));
            Assert.assertEquals("No Elder found for given elder.", true, getResults().next());
            getResults().previous();
        }
    }

    @Step("Validate Content View Assigned API")
    public void Validate_content() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No elders found", true, getJsonPath().getList("content.elders").size() >= 1);
            while (getResults().next()) {
                for (int i = 1; i <= getJsonPath().getList("content.elders").size(); i++) {

                    String val = Integer.toString(i - 1);
                    String id = getJsonPath().getString("content.elders[" + val + "].id");
                    String lname = getJsonPath().getString("content.elders[" + val + "].lastName");
                    String fname = getJsonPath().getString("content.elders[" + val + "].firstName");
                    String locationid = getJsonPath().getString("content.elders[" + val + "].location.id");
                    if (EncryptionServiceImpl.decryptToLong(id).toString().equalsIgnoreCase(getResults().getString("id"))) {
                        count++;
                        Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), lname);
                        Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), fname);
                        Assert.assertEquals("Validate person.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                        Assert.assertEquals("Validate grampa.locationId", getResults().getString("locationid"), EncryptionServiceImpl.decryptToLong(locationid).toString());
                    }
                }
            }
            Assert.assertEquals("Data miss match API:DB", getJsonPath().getList("content.elders").size(), count);
        }
    }

}
