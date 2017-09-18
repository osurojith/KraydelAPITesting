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

public class ElderAPI05Steps extends BaseClass {
long elder_id;
    @Step("User Enter Update Elder API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elderid>")
    public void Enter_API(String arg0, String arg1, String arg2, long arg3) throws Exception {
        this.api = arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3);
    }


    @Step("User enter Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void Enter_user_details(String firstName, String lastName, String email, String gender, long ethnicityId, long religionId, String dateOfBirth, long locationId, String elderstatus) throws Exception {
        body = "{\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"email\":\"" + email + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"ethnicityId\":\"" + EncryptionServiceImpl.encryptToString(ethnicityId) + "\",\n" +
                "\"religionId\":\"" + EncryptionServiceImpl.encryptToString(religionId) + "\",\n" +
                "\"dateOfBirth\":\"" + dateOfBirth + "\",\n" +
                "\"locationId\":\"" + EncryptionServiceImpl.encryptToString(locationId) + "\",\n" +
                "\"status\":\"" + elderstatus + "\",\n" +
                "\"picture\":null,";
    }


    @Step("User enter List: addresses Update Elder API <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details(long countryId, long addressID, String postalCode, String doorNumber, String street, long cityId, String addressType) throws Exception {
        System.out.println("yyy  "+addressID);
        body = body + "\"addresses\":[{\"countryId\": \""+EncryptionServiceImpl.encryptToString(countryId)+"\",\"id\": \""+EncryptionServiceImpl.encryptToString(addressID)+"\",\"doorNumber\":\""+doorNumber+"\",\"street\":\""+street+"\",\"postalCode\":\""+postalCode+"\",\"cityId\":\""+EncryptionServiceImpl.encryptToString(cityId)+"\",\"addressType\":\""+addressType+"\"}],";
    }


    @Step("User enter phoneNumber: Update Elder API <phoneID> <phoneNumber> <phoneType>")
    public void Enter_phoneNumber(long phoneID, String phoneNumber, String phoneType) throws Exception {
        body = body + "\"phoneNumbers\":[{\"id\": \"" + EncryptionServiceImpl.encryptToString(phoneID) + "\",\"phoneNumber\":\"" + phoneNumber + "\",\"phoneType\":\"" + phoneType + "\"}]";
    }

    @Step("User enter baseStation: Update Elder API <baseStationid> <tvBrandId>")
    public void Enter_baseStation(long baseStationid, long tvBrandId) throws Exception {
        if(!(baseStationid==0))
        body = body + ",\"baseStation\":{\"id\":\"" + EncryptionServiceImpl.encryptToString(baseStationid) + "\",\"tvBrandId\":\"" + EncryptionServiceImpl.encryptToString(tvBrandId) + "\"";
        body = body +"}}";
    }

    @Step("User Call Update Elder API")
    public void Call_create_user_API() {
        System.out.println(body);
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    @Step("User gets data from kraydel database Update Elder API <baseStationid><healthIssueid><email>")
    public void get_db_data(long basestationid, long healthissueid, String email) throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        if (status_code.equals("20000")) {
            String sql = null;

            if (!(basestationid == 0) && !(healthissueid == 0)) {
                sql = "select person.id as id,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId, grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename from main.person join main.address on person.id= address.person_id and person.email='" + email + "' join main.grampa on grampa.id=person.id join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id join main.phone_number on phone_number.person_id=grampa.id";
            } else if ((basestationid == 0) && !(healthissueid == 0)) {
                sql = "select person.id as id ,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id= address.person_id and person.email='" + email + "' join main.grampa on grampa.id=person.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id join main.phone_number on phone_number.person_id=grampa.id";
            } else if (!(basestationid == 0) && (healthissueid == 0)) {
                sql = "select person.id as id ,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id= address.person_id and person.email='" + email + "' join main.grampa on grampa.id=person.id join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.phone_number on phone_number.person_id=grampa.id";
            } else if ((basestationid == 0) && (healthissueid == 0)) {
                sql = "select person.id as id ,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id= address.person_id and person.email='" + email + "' join main.grampa on grampa.id=person.id join main.city on address.city= city.id join main.phone_number on phone_number.person_id=grampa.id";
            }

            System.out.println(sql);
            results = DBConn.getDBData(sql);

            if (!results.next()) {
                sql = "select * from main.person where email=" + email + "";
                results = DBConn.getDBData(sql);

                Assert.assertEquals("No record found: main.person. User email: " + email, true, results.next());

                sql = "select * from main.address where person_id='(select id from main.person where email=" + email + ")'";
                results = DBConn.getDBData(sql);
                Assert.assertEquals("No record found: main.address User email: " + email, true, results.next());


                sql = "select * from main.grampa where id='(select id from main.person where email=" + email + ")'";
                results = DBConn.getDBData(sql);
                Assert.assertEquals("No record found: main.grampa User email: " + email, true, results.next());

                if (!(basestationid == 0)) {
                    sql = "select * from main.base_station where base_station.id= (select base_station_id from main.grampa where id=(select id from main.person where email='" + email + "'))";
                    results = DBConn.getDBData(sql);
                    Assert.assertEquals("No record found: main.BaseStation User email: " + email, true, results.next());
                }
                if (!(healthissueid == 0)) {
                    sql = "select * from main.health_issues where health_issues.id= (select health_issue_id from main.grampa_health_issues where grampa_id=(select id from main.person where email='" + email + "'))";
                    results = DBConn.getDBData(sql);
                    Assert.assertEquals("No record found: main.health_issues email: " + email, true, results.next());
                }

            } else {
                results.previous();
            }
        }
    }
    @Step("User enter Elder Details Update Elder API validating <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void Enter_user_details_validating(String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            while (results.next()) {
                Assert.assertEquals("Validate person.last_name", results.getString("lname"), lastName);
                Assert.assertEquals("Validate person.first_name", results.getString("fname"), firstName);
                Assert.assertEquals("Validate grampa.date_of_birth", results.getString("dob"), dateOfBirth);
                Assert.assertEquals("Validate person.email", results.getString("email"), email);
                Assert.assertEquals("Validate person.gender", results.getString("gender"), gender);
                Assert.assertEquals("Validate person.email", results.getString("ethnicityid"), (ethnicityId).toString());
                Assert.assertEquals("Validate person.gender", results.getString("religionid"), (religionId).toString());
            }

        }



    }


    @Step("User enter List: addresses Update Elder API validating <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details_validating(String postalCode, String doorNumber, String street, String cityId, String addressType) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            while (results.previous()) {
                Assert.assertEquals("Validate address.postal_code", results.getString("postalcode"), postalCode);
                Assert.assertEquals("Validate address.door_number", results.getString("doornum"), doorNumber);
                Assert.assertEquals("Validate address.street", results.getString("street"), street);
                Assert.assertEquals("Validate address.address_type", results.getString("addresstype"), addressType.replace("PRIMARY", "1"));
                Assert.assertEquals("Validate address.city", results.getString("cityId"), (cityId).toString());
            }
        }
    }


    @Step("User enter phoneNumber: Update Elder API validating <phoneNumber> <phoneType>")
    public void Enter_phoneNumber_validating(String phoneNumber, String phoneType) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            while (results.next()) {
                Assert.assertEquals("Validate phone_number.number", results.getString("phonenumber"), phoneNumber);
                Assert.assertEquals("Validate phone_number.type", results.getString("phonenumbertype"), phoneType);
            }
        }
    }

    @Step("User enter healthIssues: Update Elder API validating <healthIssueid>")
    public void Enter_healthIssues_validating(String healthIssueid) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            if (!(healthIssueid == null)) {
                while (results.previous()) {
                    Assert.assertEquals("Validate grampa_health_issues.health_issue_id", results.getString("healthissueid"), (healthIssueid).toString());
                }
            }
        }
    }

    @Step("User enter baseStation: Update Elder API validating <baseStationid> <tvBrandId>")
    public void Enter_baseStation_validating(String baseStationid, String tvBrandId) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            if (!(baseStationid == null)) {
                while (results.next()) {
                    Assert.assertEquals("Validate grampa.base_station_id", results.getString("deviceid"), (baseStationid).toString());
                    Assert.assertEquals("Validate base_station.tv_brand_id", results.getString("devicebrandid"), (tvBrandId).toString());
                }
            }
        }
    }

}
