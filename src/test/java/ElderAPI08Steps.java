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

public class ElderAPI08Steps extends BaseClass {


    @Step("User Enter View Assigned API </api/><version></elders/unassigned/partial>")
    public void Enter_API(String arg0, String arg1, String arg2) {
        this.api =System.getenv("URI")+ arg0 + arg1 + arg2;
    }

    @Step("User Call View Assigned API")
    public void call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    @Step("User gets data from kraydel database View Assigned API")
    public void get_db_data() throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select person.id as id , person.last_name as lname , person.first_name as fname,grampa.location_id as locationid from main.person join main.grampa on grampa.id=person.id and grampa.base_station_id is null";
            System.out.println(sql);
            results = DBConn.getDBData(sql);
            Assert.assertEquals("No Elder found for given elder.", true, results.next());
            results.previous();
        }
    }

    @Step("Validate Content View Assigned API")
    public void Validate_content() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count=0;
            Assert.assertEquals("No elders found",true,jsonPath.getList("content.elders").size()>=1);
            while (results.next()) {
            for (int i = 1; i <= jsonPath.getList("content.elders").size(); i++) {

                String val = Integer.toString(i - 1);
                String id = jsonPath.getString("content.elders[" + val + "].id");
                String lname = jsonPath.getString("content.elders[" + val + "].lastName");
                String fname = jsonPath.getString("content.elders[" + val + "].firstName");
                String locationid = jsonPath.getString("content.elders[" + val + "].location.id");
                if (EncryptionServiceImpl.decryptToLong(id).toString().equalsIgnoreCase(results.getString("id"))) {
                    count++;
                    System.out.println(fname);
                    Assert.assertEquals("Validate person.last_name", results.getString("lname"), lname);
                    Assert.assertEquals("Validate person.first_name", results.getString("fname"), fname);
                    Assert.assertEquals("Validate person.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    Assert.assertEquals("Validate grampa.locationId", results.getString("locationid"), EncryptionServiceImpl.decryptToLong(locationid).toString());
                }
            }
            }
            Assert.assertEquals("Data miss match API:DB",jsonPath.getList("content.elders").size(),count);
        }
    }

}
