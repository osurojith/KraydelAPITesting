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

public class BaseStationAPI01 extends BaseClass {


    @Step("User enter Get Base-Station API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></base-stations/partial>")
    public void get_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Get Base-Station API")
    public void User_call_Base_Station_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
      String sql="select id as id,device_key as device_key,tv_brand_id as tv_brand_id from main.base_station where id="+ EncryptionServiceImpl.decryptToLong(id)+"";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.base_station. ID:"+EncryptionServiceImpl.decryptToLong(id), true, results.next());
        results.previous();
    }

    @Step("Validate content Get Base-Station API")
    public void Validate_contetnt() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count=0;
            Assert.assertEquals("No base-stations found",true,jsonPath.getList("content.base-stations").size()>=1);


                for (int i = 1; i <= jsonPath.getList("content.base-stations").size(); i++) {
                    String val = Integer.toString(i - 1);
                    String id = jsonPath.getString("content.base-stations[" + val + "].id");
                    String devicekey = jsonPath.getString("content.base-stations[" + val + "].deviceKey");
                    String tvbrand = jsonPath.getString("content.base-stations[" + val + "].tvBrandId");

                    get_db_data(id);
                    while (results.next()) {

                            count++;
                            Assert.assertEquals(results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                            Assert.assertEquals(results.getString("device_key"), devicekey);
                            Assert.assertEquals(results.getString("tv_brand_id"), EncryptionServiceImpl.decryptToLong(tvbrand).toString());
                        }

                }
            Assert.assertEquals("Data miss match API:DB",jsonPath.getList("content.base-stations").size(),count);
        }
    }
}
