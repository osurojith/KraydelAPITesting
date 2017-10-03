
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

public class BaseStationAPI04 extends BaseClass {


    @Step("User enter Update Base Station API by ID </api/><version></base-stations/><id>")
    public void User_enter_Update_Status_API(String part1, String part2, String part3, long part4) throws Exception {
        this.api = System.getenv("URI") + part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4);
        System.out.println("API: " + api);
    }

    @Step("Update Base Station API by ID Body <elderID>")
    public void Update_body(long elderID) throws Exception {
        body = "{\n" +
                " \"elderId\":\"" + EncryptionServiceImpl.encryptToString(elderID) + "\"\n" +
                "}";
        System.out.println("Body: " + body);
    }

    @Step("User call the Update Base Station API by ID")
    public void User_call_the_Update_status_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.putMethodBody(this.api, header, body);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database Update Base Station API by ID <elderID> <id>")
    public void get_db_data(String elderID, String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from main.grampa where id=" + elderID + " and base_station_id=" + id + "";
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
        Assert.assertEquals("No record found  main.grampa ID:Basestation " + elderID + " : " + id, true, getResults().next());
        getResults().previous();

    }


}
