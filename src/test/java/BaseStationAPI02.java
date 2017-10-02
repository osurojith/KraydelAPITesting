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

public class BaseStationAPI02 extends BaseClass {


    @Step("User enter Search Base-Station API </api/><version></base-stations/search>")
    public void get_API(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }

    @Step("User call the Search Base-Station API")
    public void User_call_Base_Station_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    public void get_db_data(String id, String status) throws SQLException, ClassNotFoundException {

        if (status.equalsIgnoreCase("1"))
            sql = "select base_station.id as basestationid,base_station.device_key devicekey,base_station.status as status,grampa.id as grampaid,person.first_name as fname, person.last_name as lname,grampa.location_id as locationid from main.grampa join main.person on grampa.base_station_id=" + EncryptionServiceImpl.decryptToLong(id) + " and person.id=grampa.id join main.base_station on main.base_station.id=grampa.base_station_id";
        else
            sql = "select base_station.id as basestationid,base_station.device_key devicekey,base_station.status as status from main.base_station where id=" + EncryptionServiceImpl.decryptToLong(id) + "";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.BaseStation ID:" + id, true, results.next());
        results.previous();

    }

    @Step("Validate content Search Base-Station API")
    public void Validate_contetnt() throws SQLException, ClassNotFoundException {

        if (status_code.equals("20000")) {


            for (int i = 1; i <= jsonPath.getList("content.base-stations").size(); i++) {
                int count = 0;
                String val = Integer.toString(i - 1);
                String besestationid = jsonPath.getString("content.base-stations[" + val + "].id");
                String devicekey = jsonPath.getString("content.base-stations[" + val + "].deviceKey");
                String status = jsonPath.getString("content.base-stations[" + val + "].status").replace("UNASSIGNED", "2").replace("ASSIGNED", "1");
                get_db_data(besestationid, status);
                while (results.next()) {

                    Assert.assertEquals("Validate base_station.id", results.getString("basestationid"), EncryptionServiceImpl.decryptToLong(besestationid).toString());
                    Assert.assertEquals("Validate base_station.devicekey", results.getString("devicekey"), devicekey);
                    Assert.assertEquals("Validate base_station.status", results.getString("status"), status);


                    if (jsonPath.getString("content.base-stations[" + val + "].status").equalsIgnoreCase("ASSIGNED")) {

                        for (int x = 1; x <= jsonPath.getList("content.base-stations[" + val + "].grampas").size(); x++) {
                            count++;
                            String val2 = Integer.toString(x - 1);
                            String elderid = jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].id");
                            String fname = jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].firstName");
                            String lname = jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].lastName");
                            String locationid = jsonPath.getString("content.base-stations[" + val + "].grampas[" + val2 + "].location.id");
                            System.out.println(EncryptionServiceImpl.decryptToLong(besestationid).toString());
                            Assert.assertEquals("Validate grampa.id: Base Station ID: " + EncryptionServiceImpl.decryptToLong(besestationid).toString(), results.getString("grampaid"), EncryptionServiceImpl.decryptToLong(elderid).toString());
                            Assert.assertEquals("Validate grampa.FName", results.getString("fname"), fname);
                            Assert.assertEquals("Validate grampa.LName", results.getString("lname"), lname);
                            Assert.assertEquals("Validate grampa.Location_id", results.getString("locationid"), EncryptionServiceImpl.decryptToLong(locationid).toString());
                        }
                        Assert.assertEquals("Data miss match", jsonPath.getList("content.base-stations[" + val + "].grampas").size(), count);

                    }
                }
            }
        }
    }

    @Step("Validate Search Base-Station API Pagination")
    public void Validate_Search_API_Pagination() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageNumber").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageSize").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalPages").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalRecords").isEmpty());
        }
    }
}
