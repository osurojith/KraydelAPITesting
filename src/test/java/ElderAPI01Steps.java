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


    @Step("User enter Elder Search API By ID <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elder-ID>")
    public void Enter_API(String part1, String part2, String part3, long part4) throws Exception {
        this.api = part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4);
    }

    @Step("User call the Elder Search API By ID")
    public void Call_the_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("User gets data from kraydel database Search API By ID <elder-ID>")
    public void get_data_from_database(String elderid) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
        String sql=null;

        String basestationid=jsonPath.getString("content.elder.baseStation.id");
        String healthissueid=jsonPath.getString("content.elder.healthIssues");

        System.out.println("xxx  "+basestationid);
        System.out.println("xxx  "+healthissueid);
        if (!(basestationid == null) && !(healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId, grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if ((basestationid == null) && !(healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if (!(basestationid == null) && (healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id join main.base_station on grampa.base_station_id=base_station.id";
        } else if ((basestationid == null) && (healthissueid == null)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id";
        }
        System.out.println(sql);
        results=DBConn.getDBData(sql);

        if(!results.next()){
            sql="select * from main.person where id="+elderid+"";
            results=DBConn.getDBData(sql);

            Assert.assertEquals("No record found: main.person. User ID: "+elderid ,true,results.next());

            sql="select * from main.address where person_id="+elderid+"";
            results=DBConn.getDBData(sql);
            Assert.assertEquals("No record found: main.address User ID: "+elderid,true,results.next());


            sql="select * from main.grampa where id="+elderid+"";
            results=DBConn.getDBData(sql);
            Assert.assertEquals("No record found: main.grampa User ID: "+elderid,true,results.next());

            if (!(basestationid == "")){
                sql="select * from main.base_station where base_station.id= (select base_station_id from main.grampa where id="+elderid+")";
                results=DBConn.getDBData(sql);
                Assert.assertEquals("No record found: main.BaseStation User ID: "+elderid,true,results.next());
            }
            if (!(healthissueid == "")){
                sql="select * from main.health_issues where health_issues.id= (select health_issue_id from main.grampa_health_issues where grampa_id="+elderid+")";
                results=DBConn.getDBData(sql);
                Assert.assertEquals("No record found: main.health_issues ID: "+elderid,true,results.next());
            }

            }else{
            results.previous();
        }}

    }
    @Step("Validate Elder Search API By ID Users")
    public void Validate_Search_API_Users() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            String id = jsonPath.getString("content.elder.id");
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


            while (results.next()) {
                count++;
                System.out.println("xxxxxxxxxxxxxxxxxxxxxx " + results.getString("id"));
                Assert.assertEquals("Validate person.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                Assert.assertEquals("Validate person.last_name", results.getString("lname"), lname);
                Assert.assertEquals("Validate person.first_name", results.getString("fname"), fname);
                Assert.assertEquals("Validate person.status", results.getString("status"), status);
                Assert.assertEquals("Validate grampa.date_of_birth", results.getString("dob"), dob);
                Assert.assertEquals("Validate person.email", results.getString("email"), email);
                Assert.assertEquals("Validate person.gender", results.getString("gender"), gender);
            }
            Assert.assertEquals("Invalid data count", 1, count);

            if (!(deviceid == null)) {

                while (results.previous()) {
                    Assert.assertEquals("Validate grampa.base_station_id", results.getString("deviceid"), EncryptionServiceImpl.decryptToLong(deviceid).toString());
                    Assert.assertEquals("Validate base_station.device_key", results.getString("devicekey"), devicekey);
                    Assert.assertEquals("Validate base_station.tv_brand_id", results.getString("devicebrandid"), EncryptionServiceImpl.decryptToLong(devicebrandid).toString());

                }
            }
        }
    }


    @Step("Validate Elder Search API By ID Address")
    public void Validate_address() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count=0;
            Assert.assertEquals("No elders found",true,jsonPath.getList("content.elder.addresses").size()>=1);
            while (results.next()) {
                count++;
            for (int i = 1; i <= jsonPath.getList("content.elder.addresses").size(); i++) {

                String val = Integer.toString(i - 1);
                String addressid = jsonPath.getString("content.elder.addresses[" + val + "].id");
                String postalcode = jsonPath.getString("content.elder.addresses[" + val + "].postalCode");
                String doornum = jsonPath.getString("content.elder.addresses[" + val + "].doorNumber");
                String street = jsonPath.getString("content.elder.addresses[" + val + "].street");
                String addresstype = jsonPath.getString("content.elder.addresses[" + val + "].addressType").replace("PRIMARY", "1");
                String cityId = jsonPath.getString("content.elder.addresses[" + val + "].cityId");
                String cointryId = jsonPath.getString("content.elder.addresses[" + val + "].countryId");


                        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyy " + results.getString("addressid"));
                        Assert.assertEquals("Validate address.id", results.getString("addressid"), EncryptionServiceImpl.decryptToLong(addressid).toString());
                        Assert.assertEquals("Validate address.postal_code", results.getString("postalcode"), postalcode);
                        Assert.assertEquals("Validate address.door_number", results.getString("doornum"), doornum);
                        Assert.assertEquals("Validate address.street", results.getString("street"), street);
                        Assert.assertEquals("Validate address.address_type", results.getString("addresstype"), addresstype);
                        Assert.assertEquals("Validate address.city", results.getString("cityId"), EncryptionServiceImpl.decryptToLong(cityId).toString());
                        Assert.assertEquals("Validate city.county_id", results.getString("cointryId"), EncryptionServiceImpl.decryptToLong(cointryId).toString());
                    }


            }
            Assert.assertEquals("Data miss match API:DB",jsonPath.getList("content.elder.addresses").size(),count);
        }
    }

    @Step("Validate Health Issues")
    public void Validate_health_issues() throws SQLException {
        if (status_code.equals("20000")) {


            if (!(jsonPath.getString("content.elder.healthIssues") == null)) {
                int count=0;
                Assert.assertEquals("No healthIssues found",true,jsonPath.getList("content.elder.healthIssues").size()>=1);

                while (results.previous()) {
                count++;
                for (int i = 1; i <= jsonPath.getList("content.elder.healthIssues").size(); i++) {
                    String val = Integer.toString(i - 1);
                    String healthissueid = jsonPath.getString("content.elder.healthIssues[" + val + "].id");
                    String healthissuename = jsonPath.getString("content.elder.healthIssues[" + val + "].issue");

                        Assert.assertEquals("Validate grampa_health_issues.health_issue_id", results.getString("healthissueid"), EncryptionServiceImpl.decryptToLong(healthissueid).toString());
                        Assert.assertEquals("Validate health_issues.issue", results.getString("healthissuename"), healthissuename);
                    }
                }

                Assert.assertEquals("Data miss match API:DB",jsonPath.getList("content.elder.healthIssues").size(),count);
            }
        }
    }


}
