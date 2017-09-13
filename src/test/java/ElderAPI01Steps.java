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

public class ElderAPI01Steps extends BaseClass {
    String id;

    @Step("User enter Elder Search API By ID <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elder-ID>")
    public void Enter_API(String part1, String part2, String part3, String part4) {
        this.api = part1 + part2 + part3 + part4;
    }

    @Step("User call the Elder Search API By ID")
    public void Call_the_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("Validate Elder Search API By ID Users")
    public void Validate_Search_API_Users() throws SQLException, ClassNotFoundException {

        id = jsonPath.getString("content.elder.id");
        String lname = jsonPath.getString("content.elder.lastName");
        String fname = jsonPath.getString("content.elder.firstName");
        String status = jsonPath.getString("content.elder.status").replace("INACTIVE", "3").replace("ACTIVE", "1");
        String dob = jsonPath.getString("content.elder.dateOfBirth");
        String email = jsonPath.getString("content.elder.email");
        String gender = jsonPath.getString("content.elder.gender");
        String deviceid = jsonPath.getString("content.elder.baseStation.id");
        String devicekey = jsonPath.getString("content.elder.baseStation.deviceKey");
        String devicebrandid = jsonPath.getString("content.elder.baseStation.tvBrandId");
        String locationid = jsonPath.getString("content.elder.locationId");


        Assert.assertEquals(true, !id.isEmpty());
        Assert.assertEquals(true, !lname.isEmpty());
        Assert.assertEquals(true, !fname.isEmpty());
        Assert.assertEquals(true, !status.isEmpty());
        Assert.assertEquals(true, !dob.isEmpty());
        Assert.assertEquals(true, !email.isEmpty());
        Assert.assertEquals(true, !gender.isEmpty());


        String sqlperson = "select * from main.person where id=" + EncryptionServiceImpl.decryptToLong(id) + " and first_name='" + fname + "' and last_name='" + lname + "' and email='" + email + "' and gender='" + gender + "'";
        Assert.assertEquals("Validate PERSON table: " + sqlperson, 1, DBConn.getRowCount(sqlperson));


        if ((jsonPath.getString("content.elder.baseStation.id") == null) && !(jsonPath.getString("content.elder.locationId") == null)) {
            String sqluser = "select * from main.grampa where id=" + EncryptionServiceImpl.decryptToLong(id) + " and status='" + status + "' and base_station_id IS " + EncryptionServiceImpl.decryptToLong(deviceid) + " and location_id=" + EncryptionServiceImpl.decryptToLong(locationid) + " and date_of_birth='" + dob + "'";
            Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

        }
        if ((jsonPath.getString("content.elder.locationId") == null) && !(jsonPath.getString("content.elder.baseStation.id") == null)) {
            String sqluser = "select * from main.grampa where id=" + EncryptionServiceImpl.decryptToLong(id) + " and status='" + status + "' and base_station_id=" + EncryptionServiceImpl.decryptToLong(deviceid) + " and location_id IS " + EncryptionServiceImpl.decryptToLong(locationid) + " and date_of_birth='" + dob + "'";
            Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

        }
        if ((jsonPath.getString("content.elder.locationId") == null) && (jsonPath.getString("content.elder.baseStation.id") == null)) {
            String sqluser = "select * from main.grampa where id=" + EncryptionServiceImpl.decryptToLong(id) + " and status='" + status + "' and base_station_id IS " + EncryptionServiceImpl.decryptToLong(deviceid) + " and location_id IS " + EncryptionServiceImpl.decryptToLong(locationid) + " and date_of_birth='" + dob + "'";
            Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

        }
        if (!(jsonPath.getString("content.elder.baseStation.deviceKey") == null)) {
            Assert.assertEquals(true, !devicekey.isEmpty());
        }
        if (!(jsonPath.getString("content.elder.baseStation.tvBrandId") == null)) {
            Assert.assertEquals(true, !devicebrandid.isEmpty());
        }
        // Assert.assertEquals(true, !jsonPath.getString("content.elder.ethnicityId").isEmpty());
        // Assert.assertEquals(true, !jsonPath.getString("content.elder.religionId").isEmpty());
    }


    @Step("Validate Elder Search API By ID Address")
    public void Validate_address() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.elder.addresses").size(); i++) {
                String val = Integer.toString(i - 1);
                String addressid = jsonPath.getString("content.elder.addresses[" + val + "].id");
                String postalcode = jsonPath.getString("content.elder.addresses[" + val + "].postalCode");
                String doornum = jsonPath.getString("content.elder.addresses[" + val + "].doorNumber");
                String street = jsonPath.getString("content.elder.addresses[" + val + "].street");
                String addresstype = jsonPath.getString("content.elder.addresses[" + val + "].addressType").replace("PRIMARY", "1");
                String cityId = jsonPath.getString("content.elder.addresses[" + val + "].cityId");
                String cointryId = jsonPath.getString("content.elder.addresses[" + val + "].countryId");

                Assert.assertEquals(true, !addressid.isEmpty());
                Assert.assertEquals(true, !postalcode.isEmpty());
                Assert.assertEquals(true, !doornum.isEmpty());
                Assert.assertEquals(true, !street.isEmpty());
                Assert.assertEquals(true, !addresstype.isEmpty());
                Assert.assertEquals(true, !cityId.isEmpty());
                Assert.assertEquals(true, !cointryId.isEmpty());


                String sqladdress = "select * from main.address where id=" + EncryptionServiceImpl.decryptToLong(addressid) + " and person_id='" + EncryptionServiceImpl.decryptToLong(id) + "' and door_number='" + doornum + "' and street='" + street + "' and postal_code='" + postalcode + "' and address_type='" + addresstype + "'";
                Assert.assertEquals("Validate Address Table" + sqladdress, 1, DBConn.getRowCount(sqladdress));


            }
        }
    }

    @Step("Validate Health Issues")
    public void Validate_health_issues() {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.elder.addresses").size(); i++) {
                String val = Integer.toString(i - 1);
                Assert.assertEquals(true, !jsonPath.getString("content.elder.healthIssues[" + val + "].id").isEmpty());
                Assert.assertEquals(true, !jsonPath.getString("content.elder.healthIssues[" + val + "].issue").isEmpty());
            }
        }
    }


}
