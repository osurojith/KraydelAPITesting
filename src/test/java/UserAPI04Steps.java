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

public class UserAPI04Steps extends BaseClass {


    @Step("User Enter Create User API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></users>")
    public void Enter_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User enter User Details Create User API <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>")
    public void Enter_user_details(String username, String password, String firstname, String lastname, String email, String status, String gender) {
        body = "{\n" +
                "  \"firstName\": \"" + firstname + "\",\n" +
                "  \"lastName\": \"" + lastname + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"status\": \"" + status + "\",\n" +
                "  \"gender\": \"" + gender + "\",\n" +
                "  \"username\": \"" + username + "\",\n" +
                "  \"password\": \"" + password + "\",";
    }


    @Step("User enter List: addresses Create User API <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details(String postalCode, String doorNumber, String street, String cityId, String addressType) {
        body = body + "\"addresses\": [\n" +
                "    {\n" +
                "      \"doorNumber\": \"" + doorNumber + "\",\n" +
                "      \"street\": \"" + street + "\",\n" +
                "      \"postalCode\": \"" + postalCode + "\",\n" +
                "      \"cityId\": \"" + cityId + "\",\n" +
                "      \"addressType\": \"" + addressType + "\"\n" +
                " }\n" +
                "  ],";
    }

    @Step("User enter locations: id Create User API <locationId>")
    public void Enter_Location_Id(String locationId) {
        body = body + "\n" +
                "  \"locations\": [\n" +
                "    {\n" +
                "      \"id\": \"" + locationId + "\"\n" +
                " }\n" +
                "  ],";
    }

    @Step("User enter roles: Create User API <roleId>")
    public void Enter_Role_Id(String roleId) {
        body = body + "\"roles\":[{\n" +
                "    \"id\": \"" + roleId + "\"\n" +
                " }]\n" +
                "}";
    }

    @Step("User Call Create User API")
    public void Call_create_user_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.postMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Backend Data Create User API <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender> <postalCode> <doorNumber> <street> <addressType>")
    public void Validate_backend(String usernameC, String passwordC, String firstname, String lastname, String email, String status, String gender, String postalCode, String doorNumber, String street, String addressType) throws SQLException, ClassNotFoundException {
        status=status.replace("INACTIVE","3").replace("ACTIVE","1");
        addressType=addressType.replace("PRIMARY","1");

        String sqlperson="select * from main.person where first_name='"+firstname+"' and last_name='"+lastname+"' and email='"+email+"' and gender='"+gender+"'";
        Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));

        String sqluser="select * from main.user where id="+DBConn.getValueInt(sqlperson,"id")+" and username='"+usernameC+"' and status='"+status+"'";
        Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));


        String sqladdress="select * from main.address where person_id='"+DBConn.getValueInt(sqlperson,"id")+"' and door_number='"+doorNumber+"' and street='"+street+"' and postal_code='"+postalCode+"' and address_type='"+addressType+"'";
        Assert.assertEquals("Validate Address Table"+sqladdress,1,DBConn.getRowCount(sqladdress));




    }
}
