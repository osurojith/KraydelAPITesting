
import com.aut.BaseClass;
import com.aut.DatabaseFactory;
import com.aut.EncryptionServiceImpl;
import com.aut.HttpMethodsFactory;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserAPI01Steps extends BaseClass {


    @Step("User enter User API </api/><version></users/><user-ID>")
    public void User_enter_User_API(String part1, String part2, String part3, long part4) throws Exception {
        this.api = System.getenv("URI") + part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4).toString();
        System.out.println("API: " + api);
    }

    @Step("User call the User API")
    public void User_call_the_User_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("Get data from database and Validate User API <userid>")
    public void get_data_from_database(String personid) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            String sql = null;
            sql = "select person.id as id,user_role.role_id as roleid,user_location.location_id as locationid,main.user.username as username , person.last_name as lname , person.first_name as fname, main.user.status as status, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + personid + " and address.person_id=" + personid + " join main.city on address.city= city.id join main.user on main.user.id=person.id join main.user_location on user_location.user_id=person.id join main.user_role on user_role.user_id=person.id";
            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));

            if (!getResults().next()) {
                sql = "select * from main.person where id=" + personid + "";
                setResults(DatabaseFactory.getDBData(sql));

                Assert.assertEquals("No record found: main.person. User ID: " + personid, true, getResults().next());

                sql = "select * from main.address where person_id=" + personid + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.address User ID: " + personid, true, getResults().next());

                sql = "select * from main.user where id=" + personid + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user User ID: " + personid, true, getResults().next());

                sql = "select * from main.user_location where user_id=" + personid + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user_location User ID: " + personid, true, getResults().next());

                sql = "select * from main.user_role where user_id=" + personid + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user_role User ID: " + personid, true, getResults().next());
            } else {
                getResults().previous();
            }
        }
    }

    @Step("Validate User Content")
    public void Validate_User_Content() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !getJsonPath().getString("content").isEmpty());
        }
    }

    @Step("Validate User")
    public void Validate_User() throws Exception {
        if (status_code.equals("20000")) {

            String username = getJsonPath().getString("content.user.username");
            String fname = getJsonPath().getString("content.user.firstName");
            String lname = getJsonPath().getString("content.user.lastName");
            String id = getJsonPath().getString("content.user.id");
            String email = getJsonPath().getString("content.user.email");
            String gender = getJsonPath().getString("content.user.gender");
            String status = getJsonPath().getString("content.user.status").replace("ACTIVE", "1").replace("INACTIVE", "3");

            while (getResults().next()) {
                Assert.assertEquals("Validate person.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), lname);
                Assert.assertEquals("Validate user.username", getResults().getString("username"), username);
                Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), fname);
                Assert.assertEquals("Validate person.status", getResults().getString("status"), status);
                Assert.assertEquals("Validate person.email", getResults().getString("email"), email);
                Assert.assertEquals("Validate person.gender", getResults().getString("gender"), gender);
            }


        }
    }


    @Step("Validate User Address Details")
    public void Validate_User_Address_Details() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No addresses found", true, getJsonPath().getList("content.user.addresses").size() >= 1);

            while (getResults().previous()) {
                count++;
                for (int i = 1; i <= getJsonPath().getList("content.user.addresses").size(); i++) {
                    String val = Integer.toString(i - 1);
                    String addressid = getJsonPath().getString("content.user.addresses[" + val + "].id");
                    String postalcode = getJsonPath().getString("content.user.addresses[" + val + "].postalCode");
                    String doornum = getJsonPath().getString("content.user.addresses[" + val + "].doorNumber");
                    String street = getJsonPath().getString("content.user.addresses[" + val + "].street");
                    String addresstype = getJsonPath().getString("content.user.addresses[" + val + "].addressType").replace("PRIMARY", "1");
                    String cityId = getJsonPath().getString("content.user.addresses[" + val + "].cityId");
                    String cointryId = getJsonPath().getString("content.user.addresses[" + val + "].countryId");


                    Assert.assertEquals("Validate address.id", getResults().getString("addressid"), EncryptionServiceImpl.decryptToLong(addressid).toString());
                    Assert.assertEquals("Validate address.postal_code", getResults().getString("postalcode"), postalcode);
                    Assert.assertEquals("Validate address.door_number", getResults().getString("doornum"), doornum);
                    Assert.assertEquals("Validate address.street", getResults().getString("street"), street);
                    Assert.assertEquals("Validate address.address_type", getResults().getString("addresstype"), addresstype);
                    Assert.assertEquals("Validate address.city", getResults().getString("cityId"), EncryptionServiceImpl.decryptToLong(cityId).toString());
                    Assert.assertEquals("Validate city.county_id", getResults().getString("cointryId"), EncryptionServiceImpl.decryptToLong(cointryId).toString());
                }


            }
            Assert.assertEquals("Data miss match API:DB", getJsonPath().getList("content.user.addresses").size(), count);
        }
    }


    @Step("Validate Locations")
    public void Validate_Location() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No location found", true, getJsonPath().getList("content.user.locations").size() >= 1);

            while (getResults().next()) {
                count++;
                for (int i = 1; i <= getJsonPath().getList("content.user.locations").size(); i++) {
                    String location = getJsonPath().getString("content.user.locations[" + (i - 1) + "].id");
                    Assert.assertEquals("Validate user_location.location_id", getResults().getString("locationid"), EncryptionServiceImpl.decryptToLong(location).toString());
                }
            }
            Assert.assertEquals("Data miss match API:DB", getJsonPath().getList("content.user.locations").size(), count);
        }
    }

    @Step("Validate Roles")
    public void Validate_Roles() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No roles found", true, getJsonPath().getList("content.user.roles").size() >= 1);

            while (getResults().previous()) {
                count++;
                for (int i = 1; i <= getJsonPath().getList("content.user.roles").size(); i++) {
                    String roleID = getJsonPath().getString("content.user.roles[" + (i - 1) + "].id");

                    Assert.assertEquals("Validate user_role.role_id", getResults().getString("roleid"), EncryptionServiceImpl.decryptToLong(roleID).toString());
                }

            }
            Assert.assertEquals("Data miss match API:DB", getJsonPath().getList("content.user.roles").size(), count);

        }
    }


}
