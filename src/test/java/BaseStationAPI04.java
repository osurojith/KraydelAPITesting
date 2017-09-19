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

public class BaseStationAPI04 extends BaseClass {


    @Step("User enter Update Base Station API by ID <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></base-stations/><id>")
    public void User_enter_Update_Status_API(String part1, String part2, String part3, long part4) throws Exception {
        this.api = part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4);
    }

    @Step("Update Base Station API by ID Body <elderID>")
    public void Update_body(long elderID) throws Exception {
        body = "{\n" +
                " \"elderId\":\"" + EncryptionServiceImpl.encryptToString(elderID) + "\"\n" +
                "}";

    }

    @Step("User call the Update Base Station API by ID")
    public void User_call_the_Update_status_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("User gets data from kraydel database Update Base Station API by ID <elderID> <id>")
    public void get_db_data(String elderID, String id) throws SQLException, ClassNotFoundException {
        String sql="select * from main.grampa where id="+elderID+" and base_station_id="+id+"";
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.grampa ID:Basestation "+elderID+" : "+id, true, results.next());
        results.previous();

    }
}
