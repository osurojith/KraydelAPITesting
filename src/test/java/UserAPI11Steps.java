
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

public class UserAPI11Steps extends BaseClass {
    @Step("User enter User API view Picture by id </api/><version></user/><id></picture>")
    public void enter_api(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        this.api = System.getenv("URI") + arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3) + arg4;
        System.out.println("API: " + api);
    }

    @Step("User call the User API view Picture by id")
    public void call_api() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Get data from kraydel database <userid>")
    public void get_db_data(String userid) throws SQLException, ClassNotFoundException {
        String sql = "select person.picture from main.person where id=" + userid + "";
        System.out.println(sql);
        results = DatabaseFactory.getDBData(sql);
    }

    @Step("Validate view Picture by id API")
    public void validate_picture_details() throws SQLException {
        results.next();
        Assert.assertEquals("Validate image DB:API", (results.getString("picture") == null), (jsonPath.getString("content.picture") == null));
    }
}
