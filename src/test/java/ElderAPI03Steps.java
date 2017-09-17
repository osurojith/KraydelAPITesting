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

public class ElderAPI03Steps extends BaseClass {


    @Step("User enter Elder Search API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/search>")
    public void implementation1(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Elder Search API")
    public void User_call_the_Search_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    public void get_db_data(String elderid, String basestationid, String healthissueid) throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        String sql = null;

        System.out.println("xxx  " + basestationid);
        System.out.println("xxx  " + healthissueid);
        if (!(basestationid == null) && !(healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId, grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename from main.person join main.address on person.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " and address.person_id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.grampa on grampa.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if ((basestationid == null) && !(healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " and address.person_id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.grampa on grampa.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if (!(basestationid == null) && (healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " and address.person_id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.grampa on grampa.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.city on address.city= city.id join main.base_station on grampa.base_station_id=base_station.id";
        } else if ((basestationid == null) && (healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " and address.person_id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.grampa on grampa.id=" + EncryptionServiceImpl.decryptToLong(elderid) + " join main.city on address.city= city.id";
        }
        System.out.println(sql);
        results = DBConn.getDBData(sql);


    }

    @Step("Get data from database and Validate Elder Search API Users")
    public void Validate_Search_API_Users() throws Exception {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.elders").size(); i++) {
                String val = Integer.toString(i - 1);

                String id = jsonPath.getString("content.elders[" + val + "].id");
                String lname = jsonPath.getString("content.elders[" + val + "].lastName");
                String fname = jsonPath.getString("content.elders[" + val + "].firstName");
                String status = jsonPath.getString("content.elders[" + val + "].status").replace("INACTIVE", "3").replace("ACTIVE", "1");
                String dob = jsonPath.getString("content.elders[" + val + "].dateOfBirth");
                String email = jsonPath.getString("content.elders[" + val + "].email");
                String gender = jsonPath.getString("content.elders[" + val + "].gender");
                String deviceid = jsonPath.getString("content.elders[" + val + "].baseStation.id");
                String devicekey = jsonPath.getString("content.elders[" + val + "].baseStation.deviceKey");
                String devicebrandid = jsonPath.getString("content.elders[" + val + "].baseStation.tvBrandId");
                String locationid = jsonPath.getString("content.elders[" + val + "].location.id");
                System.out.println("dddd  "+EncryptionServiceImpl.decryptToLong(id));
                get_db_data(id, deviceid, null);
                while (results.next()) {
                    Assert.assertEquals("Validate person.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    Assert.assertEquals("Validate person.last_name", results.getString("lname"), lname);
                    Assert.assertEquals("Validate person.first_name", results.getString("fname"), fname);
                    Assert.assertEquals("Validate person.status", results.getString("status"), status);
                    Assert.assertEquals("Validate grampa.date_of_birth", results.getString("dob"), dob);
                    Assert.assertEquals("Validate person.email", results.getString("email"), email);
                    Assert.assertEquals("Validate person.gender", results.getString("gender"), gender);

                    if (!(deviceid == null)) {
                        System.out.println("yyyy "+results.getString("deviceid"));
                        Assert.assertEquals("Validate grampa.base_station_id", results.getString("deviceid"), EncryptionServiceImpl.decryptToLong(deviceid).toString());
                        Assert.assertEquals("Validate base_station.device_key", results.getString("devicekey"), devicekey);
                        Assert.assertEquals("Validate base_station.tv_brand_id", results.getString("devicebrandid"), EncryptionServiceImpl.decryptToLong(devicebrandid).toString());

                    }
                }


            }
        }
    }


    @Step("Validate Elder Search API Pagination")
    public void Validate_Search_API_Pagination() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageNumber").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageSize").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalPages").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalRecords").isEmpty());
        }
    }




}
