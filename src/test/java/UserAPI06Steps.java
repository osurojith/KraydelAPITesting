import KraydelEncryption.EncryptionServiceImpl;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import utils.BaseClass;
import utils.DBConn;
import utils.HttpMethods;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserAPI06Steps extends BaseClass {
    @Step("User enter Get Picture API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></users/picture>")
    public void implementation1(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Get Picture API")
    public void User_call_the_Search_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    @Step("User gets data from kraydel database get Picture API <username>")
    public void get_DB_data(String username) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            String sql = null;

            sql = "select person.picture from main.person join main.user on person.id=main.user.id and username='"+username+"'";
            System.out.println(sql);
            results = DBConn.getDBData(sql);

            if (!results.next()) {
                sql = "select * from main.user where username='"+username+"'";
                System.out.println(sql);
                results = DBConn.getDBData(sql);
                Assert.assertEquals("No record found: main.user UserName: " + username, true, results.next());

                sql = "select * from main.person where id=(select id from main.user where username='"+username+"')";
                System.out.println(sql);
                results = DBConn.getDBData(sql);
                Assert.assertEquals("No record found: main.person. UserName: " + username, true, results.next());


                } else {
                results.previous();
            }
        }
    }

    @Step("Validate Get Picture API")
    public void Validate_Search_API_Users() throws Exception {
        if (status_code.equals("20000")) {
            String picture = jsonPath.getString("content");
            while (results.next()) {
                System.out.println(picture);
                Assert.assertEquals("Validate person.Picture", results.getString("picture"),picture );
            }



            }
    }





}
