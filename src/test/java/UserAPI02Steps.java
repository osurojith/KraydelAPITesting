
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

public class UserAPI02Steps extends BaseClass {
    @Step("User enter Search API </api/><version></users/search>")
    public void implementation1(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }

    @Step("User call the Search API")
    public void User_call_the_Search_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    public void get_DB_data(String personid) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            String sql = null;
            long id = EncryptionServiceImpl.decryptToLong(personid);
            sql = "select person.id as id,role.role_name as rolename,user_role.role_id as roleid,user_location.location_id as locationid,main.user.username as username , person.last_name as lname , person.first_name as fname, main.user.status as status, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + EncryptionServiceImpl.decryptToLong(personid) + " and address.person_id=" + EncryptionServiceImpl.decryptToLong(personid) + " join main.city on address.city= city.id join main.user on main.user.id=person.id join main.user_location on user_location.user_id=person.id join main.user_role on user_role.user_id=person.id join main.role on role.id=main.user_role.role_id";
            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));

            if (!getResults().next()) {
                sql = "select * from main.person where id=" + EncryptionServiceImpl.decryptToLong(personid) + "";
                setResults(DatabaseFactory.getDBData(sql));

                Assert.assertEquals("No record found: main.person. User ID: " + id, true, getResults().next());

                sql = "select * from main.address where person_id=" + EncryptionServiceImpl.decryptToLong(personid) + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.address User ID: " + id, true, getResults().next());

                sql = "select * from main.user where id=" + EncryptionServiceImpl.decryptToLong(personid) + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user User ID: " + id, true, getResults().next());

                sql = "select * from main.user_location where user_id=" + EncryptionServiceImpl.decryptToLong(personid) + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user_location User ID: " + id, true, getResults().next());

                sql = "select * from main.user_role where user_id=" + EncryptionServiceImpl.decryptToLong(personid) + "";
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user_role User ID: " + id, true, getResults().next());
            } else {
                getResults().previous();
            }
        }
    }

    @Step("Validate Search API Users")
    public void Validate_Search_API_Users() throws Exception {
        if (status_code.equals("20000")) {
            int count = 0;
            Assert.assertEquals("No users found", true, getJsonPath().getList("content.users").size() >= 1);
            for (int i = 1; i <= getJsonPath().getList("content.users").size(); i++) {

                String val = Integer.toString(i - 1);
                String id = getJsonPath().getString("content.users[" + val + "].id");
                String username = getJsonPath().getString("content.users[" + val + "].username");
                String lname = getJsonPath().getString("content.users[" + val + "].lastName");
                String fname = getJsonPath().getString("content.users[" + val + "].firstName");
                String email = getJsonPath().getString("content.users[" + val + "].email");
                String gender = getJsonPath().getString("content.users[" + val + "].gender");
                String status = getJsonPath().getString("content.users[" + val + "].status").replace("INACTIVE", "3").replace("ACTIVE", "1");

                get_DB_data(id);

                while (getResults().next()) {

                    Assert.assertEquals("Validate person.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), lname);
                    Assert.assertEquals("Validate user.username", getResults().getString("username"), username);
                    Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), fname);
                    Assert.assertEquals("Validate person.status", getResults().getString("status"), status);
                    Assert.assertEquals("Validate person.email", getResults().getString("email"), email);
                    Assert.assertEquals("Validate person.gender", getResults().getString("gender"), gender);

                    for (int x = 1; x <= getJsonPath().getList("content.users[" + val + "].roleNames").size(); x++) {
                        String val1 = Integer.toString(x - 1);
                        String roleName = getJsonPath().getString("content.users[" + val + "].roleNames[" + val1 + "]");
                        Assert.assertEquals("Validate user_role.role_id", getResults().getString("rolename"), roleName);

                    }
                }


            }
        }
    }

    @Step("Validate Search API Pagination")
    public void Validate_Search_API_Pagination() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !getJsonPath().getString("pagination.pageNumber").isEmpty());
            Assert.assertEquals(true, !getJsonPath().getString("pagination.pageSize").isEmpty());
            Assert.assertEquals(true, !getJsonPath().getString("pagination.totalPages").isEmpty());
            Assert.assertEquals(true, !getJsonPath().getString("pagination.totalRecords").isEmpty());
        }
    }

}
