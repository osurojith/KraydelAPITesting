
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

public class UserAPI04Steps extends BaseClass {


    @Step("User Enter Create User API </api/><version></users>")
    public void Enter_API(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
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
    public void Enter_Address_Details(String postalCode, String doorNumber, String street, long cityId, String addressType) throws Exception {
        body = body + "\"addresses\": [\n" +
                "    {\n" +
                "      \"doorNumber\": \"" + doorNumber + "\",\n" +
                "      \"street\": \"" + street + "\",\n" +
                "      \"postalCode\": \"" + postalCode + "\",\n" +
                "      \"cityId\": \"" + EncryptionServiceImpl.encryptToString(cityId) + "\",\n" +
                "      \"addressType\": \"" + addressType + "\"\n" +
                " }\n" +
                "  ],";
    }

    @Step("User enter locations: id Create User API <locationId>")
    public void Enter_Location_Id(long locationId) throws Exception {
        body = body + "\n" +
                "  \"locations\": [\n" +
                "    {\n" +
                "      \"id\": \"" + EncryptionServiceImpl.encryptToString(locationId) + "\"\n" +
                " }\n" +
                "  ],";
    }

    @Step("User enter roles: Create User API <roleId>")
    public void Enter_Role_Id(long roleId) throws Exception {
        body = body + "\"roles\":[{\n" +
                "    \"id\": \"" + EncryptionServiceImpl.encryptToString(roleId) + "\"\n" +
                " }]\n" +
                "}";
        System.out.println("Body: " + body);
    }

    @Step("User Call Create User API")
    public void Call_create_user_API() {

        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.postMethodBody(this.api, header, body);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database Create User API <email>")
    public void get_data_from_database(String email) throws SQLException, ClassNotFoundException {
        if (status_code.equalsIgnoreCase("20000")) {
            String sql = null;

            sql = "select person.id as id,user_role.role_id as roleid,user_location.location_id as locationid,main.user.username as username , person.last_name as lname , person.first_name as fname, main.user.status as status, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.email='" + email + "' and address.person_id=person.id join main.city on address.city= city.id join main.user on main.user.id=person.id join main.user_location on user_location.user_id=person.id join main.user_role on user_role.user_id=person.id";
            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));

            if (!getResults().next()) {
                sql = "select * from main.person where email='" + email + "'";
                setResults(DatabaseFactory.getDBData(sql));

                Assert.assertEquals("No record found: main.person. User Email : " + email, true, getResults().next());

                sql = "select * from main.address where person_id=(select * from main.person where email='" + email + "')";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.address Email : " + email, true, getResults().next());

                sql = "select * from main.user where id=(select * from main.person where email='" + email + "')";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user Email : " + email, true, getResults().next());

                sql = "select * from main.user_location where user_id=(select * from main.person where email='" + email + "')";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user_location Email : " + email, true, getResults().next());

                sql = "select * from main.user_role where user_id=(select * from main.person where email='" + email + "')";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user_role Email : " + email, true, getResults().next());
            } else {
                getResults().previous();
            }
        }
    }


    @Step("Validate User Details Create User API <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>")
    public void Validate_user_details(String username, String password, String firstname, String lastname, String email, String status, String gender) throws SQLException {
        if (status_code.equals("20000")) {
            while (getResults().next()) {
                status = status.replace("ACTIVE", "1").replace("INACTIVE", "3");
                Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), lastname);
                Assert.assertEquals("Validate user.username", getResults().getString("username"), username);
                Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), firstname);
                Assert.assertEquals("Validate person.status", getResults().getString("status"), status);
                Assert.assertEquals("Validate person.email", getResults().getString("email"), email);
                Assert.assertEquals("Validate person.gender", getResults().getString("gender"), gender);
            }
        }
    }


    @Step("Validate List: addresses Create User API <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Validate_Address_Details(String postalCode, String doorNumber, String street, String cityId, String addressType) throws SQLException {
        if (status_code.equals("20000")) {
            while (getResults().previous()) {
                addressType = addressType.replace("PRIMARY", "1");
                Assert.assertEquals("Validate address.postal_code", getResults().getString("postalcode"), postalCode);
                Assert.assertEquals("Validate address.door_number", getResults().getString("doornum"), doorNumber);
                Assert.assertEquals("Validate address.street", getResults().getString("street"), street);
                Assert.assertEquals("Validate address.address_type", getResults().getString("addresstype"), addressType);
                Assert.assertEquals("Validate address.city", getResults().getString("cityId"), cityId);
            }
        }
    }

    @Step("Validate locations: id Create User API <locationId>")
    public void Validate_Location_Id(String locationId) throws SQLException {
        if (status_code.equals("20000")) {
            while (getResults().next()) {
                Assert.assertEquals("Validate user_location.location_id", getResults().getString("locationid"), locationId);
            }
        }
    }

    @Step("Validate roles: Create User API <roleId>")
    public void Validate_Role_Id(String roleId) throws SQLException {
        if (status_code.equals("20000")) {
            while (getResults().previous()) {
                Assert.assertEquals("Validate user_role.role_id", getResults().getString("roleid"), roleId);

            }
        }
    }


}
