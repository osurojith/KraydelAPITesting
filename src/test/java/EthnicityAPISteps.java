
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

public class EthnicityAPISteps extends BaseClass {


    @Step("User enter Ethnicity API </api/><version></ethnicities/partial>")
    public void implementation1(String part1, String version, String part2) {
        this.api = System.getenv("URI") + part1 + version + part2;
        System.out.println("API: " + api);
    }


    @Step("User call the Ethnicity API")
    public void User_call_the_Ethnicity_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql = "select * from main.ethnicity where id=" + EncryptionServiceImpl.decryptToLong(id) + "";
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
        Assert.assertEquals("No record found  main.ethnicity. ID:" + EncryptionServiceImpl.decryptToLong(id), true, getResults().next());
        getResults().previous();
    }

    @Step("Validate Ethnicity Content")
    public void Validate_Ethnicity_Content() throws SQLException, ClassNotFoundException {

        for (int i = 1; i <= getJsonPath().getList("content.ethnicities").size(); i++) {
            int count = 0;
            String val = Integer.toString(i - 1);
            String ethnicityid = getJsonPath().getString("content.ethnicities[" + val + "].id");
            String ethnicityname = getJsonPath().getString("content.ethnicities[" + val + "].name");


            get_db_data(ethnicityid);
            while (getResults().next()) {
                count++;
                Assert.assertEquals(getResults().getString("id"), EncryptionServiceImpl.decryptToLong(ethnicityid).toString());
                Assert.assertEquals(getResults().getString("name"), ethnicityname);

            }

            Assert.assertEquals("Data miss match API:DB", 1, count);


        }
    }


}
