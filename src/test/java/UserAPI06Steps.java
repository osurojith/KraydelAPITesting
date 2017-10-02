import com.aut.BaseClass;
import com.aut.DatabaseFactory;
import com.aut.HttpMethodsFactory;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserAPI06Steps extends BaseClass {
    @Step("User enter Get Picture API </api/><version></users/picture>")
    public void implementation1(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }

    @Step("User call the Get Picture API")
    public void User_call_the_Search_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database get Picture API <username>")
    public void get_DB_data(String username) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select person.picture from main.person join main.user on person.id=main.user.id and username='" + username + "'";
            System.out.println(sql);
            setResults(DatabaseFactory.getDBData(sql));

            if (!getResults().next()) {
                sql = "select * from main.user where username='" + username + "'";
                System.out.println(sql);
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.user UserName: " + username, true, getResults().next());

                sql = "select * from main.person where id=(select id from main.user where username='" + username + "')";
                System.out.println(sql);
                setResults(DatabaseFactory.getDBData(sql));
                Assert.assertEquals("No record found: main.person. UserName: " + username, true, getResults().next());


            } else {
                getResults().previous();
            }
        }
    }

    @Step("Validate Get Picture API")
    public void Validate_Search_API_Users() throws Exception {
        if (status_code.equals("20000")) {
            String picture = getJsonPath().getString("content");
            while (getResults().next()) {
                Assert.assertEquals("Validate person.Picture", getResults().getString("picture"), picture);
            }


        }
    }


}
