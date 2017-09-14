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
    public void Enter_API(String arg0, String arg1, String arg2, String arg3) {
        this.api = arg0 + arg1 + arg2 + arg3;
    }


    @Step("User enter Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
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
                "\"status\":\"" + elderstatus + "\",";
    }


    @Step("User enter List: addresses Update Elder API <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details(String countryId, String addressID, String postalCode, String doorNumber, String street, String cityId, String addressType) {
        System.out.println("yyy  "+addressID);
        body = body + "\"addresses\":[{\"countryId\": \""+countryId+"\",\"id\": \""+addressID+"\",\"doorNumber\":\""+doorNumber+"\",\"street\":\""+street+"\",\"postalCode\":\""+postalCode+"\",\"cityId\":\""+cityId+"\",\"addressType\":\""+addressType+"\"}],";
    }


    @Step("User enter phoneNumber: Update Elder API <phoneID> <phoneNumber> <phoneType>")
    public void Enter_phoneNumber(String phoneID, String phoneNumber, String phoneType) {
        body = body + "\"phoneNumbers\":[{\"id\": \"" + phoneID + "\",\"phoneNumber\":\"" + phoneNumber + "\",\"phoneType\":\"" + phoneType + "\"}],";
    }

    @Step("User enter baseStation: Update Elder API <baseStationid> <tvBrandId>")
    public void Enter_baseStation(String baseStationid, String tvBrandId) {
        body = body + "\"baseStation\":{\"id\":\"" + baseStationid + "\",\"tvBrandId\":\"" + tvBrandId + "\"}}";
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
    @Step("User enter Elder Details Update Elder API validating <elderid> <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void Enter_user_details_validating(String elderid, String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) throws SQLException, ClassNotFoundException {
        elderstatus=elderstatus.replace("INACTIVE", "3").replace("ACTIVE", "1");

        String sqlperson="select * from main.person where first_name='"+firstName+"' and last_name='"+lastName+"' and email='"+email+"' and gender='"+gender+"' and ethnicity_id="+ EncryptionServiceImpl.decryptToLong(ethnicityId)+" and religion_id="+EncryptionServiceImpl.decryptToLong(religionId)+"";
        Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));
        elder_id=EncryptionServiceImpl.decryptToLong(elderid);
        String sqluser = "select * from main.grampa where id=" + elder_id + " and status='" + elderstatus + "' and location_id=" + EncryptionServiceImpl.decryptToLong(locationId) + " and date_of_birth='" + dateOfBirth + "'";
        Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));


    }


    @Step("User enter List: addresses Update Elder API validating <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details_validating(String countryId,String addressID,String postalCode, String doorNumber, String street, String cityId, String addressType) throws SQLException, ClassNotFoundException {
        String sqladdress = "select * from main.address where person_id='" +elder_id + "' and door_number='" + doorNumber + "' and street='" + street + "' and postal_code='" + postalCode + "' and address_type='" + addressType.replace("PRIMARY", "1") + "'";
        String sqlcity="select * from main.city where id="+DBConn.getValueInt(sqladdress,"city")+"";

        Assert.assertEquals("Validate Address Table" + sqladdress, 1, DBConn.getRowCount(sqladdress));

        Assert.assertEquals("Validate CItyID and Country ID ",Integer.toString(DBConn.getValueInt(sqlcity,"country_id")) ,EncryptionServiceImpl.decryptToLong(cityId).toString());

    }


    @Step("User enter phoneNumber: Update Elder API validating <phoneID> <phoneNumber> <phoneType>")
    public void Enter_phoneNumber_validating(String phoneID,String phoneNumber, String phoneType) throws SQLException, ClassNotFoundException {
        String sqluser = "select * from main.phone_number where id="+EncryptionServiceImpl.decryptToLong(phoneID)+" and person_id=" + elder_id + " and phone_number='" + phoneNumber + "' and phone_type='" + phoneType + "'";
        Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

    }


    @Step("User enter baseStation: Update Elder API validating <baseStationid> <tvBrandId>")
    public void Enter_baseStation_validating(String baseStationid, String tvBrandId) throws SQLException, ClassNotFoundException {
        String sqluser = "select * from main.grampa where id=" + elder_id + " and base_station_id=" + EncryptionServiceImpl.decryptToLong(baseStationid) + "";
        Assert.assertEquals("Validate USER table: " + sqluser, 1, DBConn.getRowCount(sqluser));

    }

}
