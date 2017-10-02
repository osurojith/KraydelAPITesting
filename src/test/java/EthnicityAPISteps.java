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

public class EthnicityAPISteps extends BaseClass {


    @Step("User enter Ethnicity API </api/><version></ethnicities/partial>")
    public void implementation1(String part1, String version, String part2) {
        this.api =System.getenv("URI")+ part1 + version + part2;
    }


    @Step("User call the Ethnicity API")
    public void User_call_the_Ethnicity_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql="select * from main.ethnicity where id="+ EncryptionServiceImpl.decryptToLong(id)+"";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.ethnicity. ID:"+EncryptionServiceImpl.decryptToLong(id), true, results.next());
        results.previous();
    }

    @Step("Validate Ethnicity Content")
    public void Validate_Ethnicity_Content() throws SQLException, ClassNotFoundException {

            for (int i = 1; i <= jsonPath.getList("content.ethnicities").size(); i++) {
                int count=0;
                String val = Integer.toString(i - 1);
                String ethnicityid=jsonPath.getString("content.ethnicities[" + val + "].id");
                String ethnicityname=jsonPath.getString("content.ethnicities[" + val + "].name");


                get_db_data(ethnicityid);
                while (results.next()) {
                    count++;
                    Assert.assertEquals(results.getString("id"), EncryptionServiceImpl.decryptToLong(ethnicityid).toString());
                    Assert.assertEquals(results.getString("name"), ethnicityname);

                }

                Assert.assertEquals("Data miss match API:DB",1,count);


            }
    }


}
