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

public class BaseStationAPI03 extends BaseClass {


    @Step("User enter Update Base Station API </api/><version></users/><id></status>")
    public void User_enter_Update_Status_API(String part1, String part2, String part3, long part4, String part5) throws Exception {
        this.api = System.getenv("URI")+part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4) + part5;
        System.out.println("API: "+api);
    }

    @Step("Update Base Station API Body <Userstatus>")
    public void Update_body(String status) {
        body = "{\n" +
                " \"status\":\"" + status + "\"\n" +
                "}";
        System.out.println("Body: "+body);
    }

    @Step("User call the Update Base Station API")
    public void User_call_the_Update_status_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("User gets data from kraydel database  Update Base Station Status API <id>")
    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from main.base_station where id=" + id + "";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.BaseStation ID:" + id, true, results.next());
        results.previous();

    }

    @Step("Validate back Base Station Status <basestationtatus>")
    public void validate_status(String basestationtatus) throws SQLException {
        basestationtatus=basestationtatus.replace("OFFLINE","4").replace("ONLINE","3").replace("DEPROVISIONED","5");
        int count=0;
        while (results.next()){
            count++;
            Assert.assertEquals(results.getString("status"), basestationtatus);

        }
        Assert.assertEquals("Data miss match",1,count);
    }
}
