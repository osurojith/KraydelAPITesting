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

public class UserAPI05Steps extends BaseClass {

    @Step("User Enter Update User API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></users/><id>")
    public void Update_User_API(String part1, String part2, String part3, String part4) {
        this.api = part1 + part2 + part3 + part4;
    }

    @Step("User enter User Details Update User API <userid> <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>")
    public void Enter_user_details(String id, String username, String password, String firstname, String lastname, String email, String status, String gender) {
        body = "{\n" +
                "  \"id\": \"" + id + "\",\n" +
                "  \"firstName\": \"" + firstname + "\",\n" +
                "  \"lastName\": \"" + lastname + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"gender\": \"" + gender + "\",\n" +
                "  \"username\": \"" + username + "\",";
    }


    @Step("User enter List: addresses Update User API <addressid> <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details(String id, String postalCode, String doorNumber, String street, String cityId, String addressType) {
        body = body + "\"addresses\": [\n" +
                "    {\n" +
                "      \"id\": \"" + id + "\",\n" +
                "      \"doorNumber\": \"" + doorNumber + "\",\n" +
                "      \"street\": \"" + street + "\",\n" +
                "      \"postalCode\": \"" + postalCode + "\",\n" +
                "      \"cityId\": \"" + cityId + "\",\n" +
                "      \"addressType\": \"" + addressType + "\"\n" +
                " }\n" +
                "  ],";
    }

    @Step("User enter locations: id Update User API <locationId>")
    public void Enter_Location_Id(String locationId) {
        body = body + "\n" +
                "  \"locations\": [\n" +
                "    {\n" +
                "      \"id\": \"" + locationId + "\"\n" +
                " }\n" +
                "  ],";
    }

    @Step("User enter roles: Update User API <roleId>")
    public void Enter_Role_Id(String roleId) {
        body = body + "\"roles\":[{\n" +
                "    \"id\": \"" + roleId + "\"\n" +
                " }]\n" +
                "}";
    }

    @Step("User Call Update User API")
    public void Call_create_user_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.putMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("Validate Status Code Update User API <status_code>")
    public void Validate_Status_code(String status_code) {
        this.status_code = status_code;
        Assert.assertEquals(status_code, jsonPath.getString("statusCode"));

    }



    @Step("Validate Back end Update user API <userid> <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender> <postalCode> <doorNumber> <street> <addressType>")
    public void Validate_backend(String userid, String usernameC, String passwordC, String firstname, String lastname, String email, String status, String gender, String postalCode, String doorNumber, String street, String addressType) throws Exception {
        status=status.replace("ACTIVE","1").replace("INACTIVE","3");
        addressType=addressType.replace("PRIMARY","1");

        String sqlperson="select * from main.person where first_name='"+firstname+"' and last_name='"+lastname+"' and email='"+email+"' and gender='"+gender+"'";
        Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));

        String sqluser="select * from main.user where id="+ EncryptionServiceImpl.decryptToLong(userid)+" and username='"+usernameC+"' and status='"+status+"'";
        Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));


        String sqladdress="select * from main.address where person_id='"+EncryptionServiceImpl.decryptToLong(userid)+"' and door_number='"+doorNumber+"' and street='"+street+"' and postal_code='"+postalCode+"' and address_type='"+addressType+"'";
        Assert.assertEquals("Validate Address Table"+sqladdress,1,DBConn.getRowCount(sqladdress));




    }
}
