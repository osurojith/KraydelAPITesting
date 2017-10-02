
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

public class BaseStationAPI01 extends BaseClass {


    @Step("User enter Get Base-Station API </api/><version></base-stations/partial>")
    public void get_API(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }

    @Step("User call the Get Base-Station API")
    public void User_call_Base_Station_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql = "select id as id,device_key as device_key,tv_brand_id as tv_brand_id from main.base_station where id=" + EncryptionServiceImpl.decryptToLong(id) + "";
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
        Assert.assertEquals("No record found  main.base_station. ID:" + EncryptionServiceImpl.decryptToLong(id), true, getResults().next());
        getResults().previous();
    }

    @Step("Validate content Get Base-Station API")
    public void Validate_contetnt() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No base-stations found", true, getJsonPath().getList("content.base-stations").size() >= 1);


            for (int i = 1; i <= getJsonPath().getList("content.base-stations").size(); i++) {
                String val = Integer.toString(i - 1);
                String id = getJsonPath().getString("content.base-stations[" + val + "].id");
                String devicekey = getJsonPath().getString("content.base-stations[" + val + "].deviceKey");
                String tvbrand = getJsonPath().getString("content.base-stations[" + val + "].tvBrandId");

                get_db_data(id);
                while (getResults().next()) {

                    count++;
                    Assert.assertEquals(getResults().getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    Assert.assertEquals(getResults().getString("device_key"), devicekey);
                    Assert.assertEquals(getResults().getString("tv_brand_id"), EncryptionServiceImpl.decryptToLong(tvbrand).toString());
                }

            }
            Assert.assertEquals("Data miss match API:DB", getJsonPath().getList("content.base-stations").size(), count);
        }
    }
}
