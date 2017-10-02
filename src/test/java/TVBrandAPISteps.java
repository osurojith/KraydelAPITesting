
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

public class TVBrandAPISteps extends BaseClass {


    @Step("User enter TVBrand API </api/><version></tvbrands/partial>")
    public void User_enter_TVBrand_API(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }

    @Step("User call the TVBrand API")
    public void User_call_the_TVBrand_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from main.tv_brand where id=" + EncryptionServiceImpl.decryptToLong(id) + "";
        System.out.println(sql);
        results = DatabaseFactory.getDBData(sql);
        Assert.assertEquals("No record found  main.tv_brand. ID:" + EncryptionServiceImpl.decryptToLong(id), true, results.next());
        results.previous();
    }

    @Step("Validate TVBrand Content")
    public void Validate_TVBrand_Content() throws SQLException, ClassNotFoundException {

        for (int i = 1; i <= jsonPath.getList("content.tvsBrands").size(); i++) {
            int count = 0;
            String val = Integer.toString(i - 1);
            String tvbrandid = jsonPath.getString("content.tvsBrands[" + val + "].id");
            String tvbrandname = jsonPath.getString("content.tvsBrands[" + val + "].name");
            get_db_data(tvbrandid);

            while (results.next()) {
                count++;
                Assert.assertEquals(results.getString("id"), EncryptionServiceImpl.decryptToLong(tvbrandid).toString());
                Assert.assertEquals(results.getString("name"), tvbrandname);

            }

            Assert.assertEquals("Data miss match API:DB", 1, count);
        }
    }

}
