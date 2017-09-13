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

public class UserAPI01Steps extends BaseClass {
    String id;

    @Step("User enter User API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></users/><user-ID>")
    public void User_enter_User_API(String part1, String part2, String part3, String part4) {
        this.api = part1 + part2 + part3 + part4;
    }

    @Step("User call the User API")
    public void User_call_the_User_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Status Code User API <status_code>")
    public void Validate_Status_Code_User_API(String status_code) {
        this.status_code = status_code;
        Assert.assertEquals(status_code, jsonPath.getString("statusCode"));
    }

    @Step("Validate HttpRequest Code User API <request_code>")
    public void Validate_HttpRequest_Code_User_API(int request_code) {
        Assert.assertEquals(request_code, response.getStatusCode());
    }

    @Step("Validate User Content")
    public void Validate_User_Content() {
        Assert.assertEquals(true, !jsonPath.getString("content").isEmpty());
    }

    @Step("Validate User")
    public void Validate_User() throws Exception {
        if (status_code.equals("20000")) {
           String username=jsonPath.getString("content.user.username");
            String fname=jsonPath.getString("content.user.firstName");
            String lname=jsonPath.getString("content.user.lastName");
            id=jsonPath.getString("content.user.id");
            String email=jsonPath.getString("content.user.email");
            String gender=jsonPath.getString("content.user.gender");
            String status=jsonPath.getString("content.user.status").replace("ACTIVE","1").replace("INACTIVE","3");


            Assert.assertEquals(true, !username.isEmpty());
            Assert.assertEquals(true, !fname.isEmpty());
            Assert.assertEquals(true, !lname.isEmpty());
            Assert.assertEquals(true, !id.isEmpty());
            Assert.assertEquals(true, !email.isEmpty());
            Assert.assertEquals(true, !gender.isEmpty());


            String sqlperson="select * from main.person where id="+EncryptionServiceImpl.decryptToLong(id)+" and first_name='"+fname+"' and last_name='"+lname+"' and email='"+email+"' and gender='"+gender+"'";
            Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));

            String sqluser="select * from main.user where id="+EncryptionServiceImpl.decryptToLong(id)+" and username='"+username+"' and status='"+status+"'";
            Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));


        }
    }


    @Step("Validate User Address Details")
    public void Validate_User_Address_Details() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.user.addresses").size(); i++) {
                String val = Integer.toString(i - 1);
                String addressid=jsonPath.getString("content.user.addresses[" + val + "].id");
                String postalcode=jsonPath.getString("content.user.addresses[" + val + "].postalCode");
                String doornum=jsonPath.getString("content.user.addresses[" + val + "].doorNumber");
                String street=jsonPath.getString("content.user.addresses[" + val + "].street");
                String addresstype=jsonPath.getString("content.user.addresses[" + val + "].addressType").replace("PRIMARY","1");
                String cityId=jsonPath.getString("content.user.addresses[" + val + "].cityId");
                String cointryId=jsonPath.getString("content.user.addresses[" + val + "].countryId");



                Assert.assertEquals(true, !addressid.isEmpty());
                Assert.assertEquals(true, !postalcode.isEmpty());
                Assert.assertEquals(true, !doornum.isEmpty());
                Assert.assertEquals(true, !street.isEmpty());
                Assert.assertEquals(true, !addresstype.isEmpty());
                Assert.assertEquals(true, !cityId.isEmpty());
                Assert.assertEquals(true, !cointryId.isEmpty());


                String sqladdress="select * from main.address where id="+EncryptionServiceImpl.decryptToLong(addressid)+" and person_id='"+EncryptionServiceImpl.decryptToLong(id)+"' and door_number='"+doornum+"' and street='"+street+"' and postal_code='"+postalcode+"' and address_type='"+addresstype+"'";
                Assert.assertEquals("Validate Address Table"+sqladdress,1,DBConn.getRowCount(sqladdress));

            }
        }
    }


    @Step("Validate Locations")
    public void Validate_Location() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.user.locations").size(); i++) {
                String location=jsonPath.getString("content.user.locations[" + (i - 1) + "].name");

                Assert.assertEquals(true, !location.isEmpty());

                String sqllocation="select * from main.location where name='"+location+"'";
                String sqluserlocation="select * from main.user_location where user_id='"+EncryptionServiceImpl.decryptToLong(id)+"' and location_id='"+DBConn.getValueInt(sqllocation,"id")+"'";
                Assert.assertEquals("Validate location table: "+sqluserlocation,1,DBConn.getRowCount(sqluserlocation));




            }
        }
    }

    @Step("Validate Roles")
    public void Validate_Roles() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.user.roles").size(); i++) {
                String roleID=jsonPath.getString("content.user.roles[" + (i - 1) + "].id");


                Assert.assertEquals(true, !roleID.isEmpty());


              /*  String sqlrole="select * from main.role where role_name='"+roleName+"'";
                String sqluserrole="select * from main.user_role where user_id="+DBConn.getValueInt(sqlperson,"id")+" and role_id='"+DBConn.getValueInt(sqlrole,"id")+"'";


                Assert.assertEquals("Validate User_role table: "+sqluserrole,1,DBConn.getRowCount(sqluserrole));
*/
            }
        }
    }


}
