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

public class ElderAPI04Steps extends BaseClass {
int elder_id;
    @Step("User Enter Create Elder API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders>")
    public void Enter_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User enter Elder Details Create Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void Enter_user_details(String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) {
        body = "{\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"email\":\"" + email + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"ethnicityId\":\"" + ethnicityId + "\",\n" +
                "\"religionId\":\"" + religionId + "\",\n" +
                "\"dateOfBirth\":\"" + dateOfBirth + "\",\n" +
                "\"locationId\":\"" + locationId + "\",\n" +
                "\"status\":\"" + elderstatus + "\",\n" +
                "\"picture\":null,";
    }


    @Step("User enter List: addresses Create Elder API <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details(String postalCode, String doorNumber, String street, String cityId, String addressType) {
        body = body + "\"addresses\":[{\"doorNumber\":\"" + doorNumber + "\",\"street\":\"" + street + "\",\"postalCode\":\"" + postalCode + "\",\"cityId\":\"" + cityId + "\",\"addressType\":\"" + addressType + "\"}],";
    }


    @Step("User enter phoneNumber: Create Elder API <phoneNumber> <phoneType>")
    public void Enter_phoneNumber(String phoneNumber, String phoneType) {
        body = body + "\"phoneNumbers\":[{\"phoneNumber\":\"" + phoneNumber + "\",\"phoneType\":\"" + phoneType + "\"}],";
    }

    @Step("User enter healthIssues: Create Elder API <healthIssueid>")
    public void Enter_healthIssues(String healthIssueid) {
        body = body + "\"healthIssues\":[{\"id\":\"" + healthIssueid + "\"}],";
    }

    @Step("User enter baseStation: Create Elder API <baseStationid> <tvBrandId> <baseStationstatus>")
    public void Enter_baseStation(String baseStationid, String tvBrandId, String baseStationstatus) {
        body = body + "\"baseStation\":{\"id\":\"" + baseStationid + "\",\"tvBrandId\":\"" + tvBrandId + "\",\"status\":\"" + baseStationstatus + "\"}}";
    }

    @Step("User Call Create Elder API")
    public void Call_create_user_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.postMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    @Step("User enter Elder Details Create Elder API validating <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void Enter_user_details_validating(String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) throws SQLException, ClassNotFoundException {
        elderstatus=elderstatus.replace("INACTIVE", "3").replace("ACTIVE", "1");

        String sqlperson="select * from main.person where first_name='"+firstName+"' and last_name='"+lastName+"' and email='"+email+"' and gender='"+gender+"' and ethnicity_id="+EncryptionServiceImpl.decryptToLong(ethnicityId)+" and religion_id="+EncryptionServiceImpl.decryptToLong(religionId)+"";
        Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));
        elder_id=DBConn.getValueInt(sqlperson,"id");
        String sqluser = "select * from main.grampa where id=" + elder_id + " and status='" + elderstatus + "' and location_id=" + EncryptionServiceImpl.decryptToLong(locationId) + " and date_of_birth='" + dateOfBirth + "'";
        Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));


    }


    @Step("User enter List: addresses Create Elder API validating <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details_validating(String postalCode, String doorNumber, String street, String cityId, String addressType) throws SQLException, ClassNotFoundException {
        String sqladdress = "select * from main.address where person_id='" +elder_id + "' and door_number='" + doorNumber + "' and street='" + street + "' and postal_code='" + postalCode + "' and address_type='" + addressType.replace("PRIMARY", "1") + "'";
        Assert.assertEquals("Validate Address Table" + sqladdress, 1, DBConn.getRowCount(sqladdress));

    }


    @Step("User enter phoneNumber: Create Elder API validating <phoneNumber> <phoneType>")
    public void Enter_phoneNumber_validating(String phoneNumber, String phoneType) throws SQLException, ClassNotFoundException {
        String sqluser = "select * from main.phone_number where person_id=" + elder_id + " and phone_number='" + phoneNumber + "' and phone_type='" + phoneType + "'";
        Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

    }

    @Step("User enter healthIssues: Create Elder API validating <healthIssueid>")
    public void Enter_healthIssues_validating(String healthIssueid) throws SQLException, ClassNotFoundException {
        String sqluser = "select * from main.grampa_health_issues where grampa_id=" + elder_id + " and health_issue_id=" + EncryptionServiceImpl.decryptToLong(healthIssueid) + "";
        Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

         }

    @Step("User enter baseStation: Create Elder API validating <baseStationid> <tvBrandId> <baseStationstatus>")
    public void Enter_baseStation_validating(String baseStationid, String tvBrandId, String baseStationstatus) throws SQLException, ClassNotFoundException {
        String sqluser = "select * from main.grampa where id=" + elder_id + " and base_station_id=" + EncryptionServiceImpl.decryptToLong(baseStationid) + "";
        Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

       }

}
