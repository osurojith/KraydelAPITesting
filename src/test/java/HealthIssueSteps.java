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

public class HealthIssueSteps extends BaseClass {


    @Step("User enter HealthIssue API </api/><version></healthissues/partial>")
    public void User_enter_HealthIssue_API(String part1, String version, String part2) {
        this.api =System.getenv("URI")+ part1 + version + part2;
    }

    @Step("User call the HealthIssue API")
    public void User_call_the_HealthIssue_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql="select * from main.health_issues where id="+ EncryptionServiceImpl.decryptToLong(id)+"";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.health_issues. ID:"+EncryptionServiceImpl.decryptToLong(id), true, results.next());
        results.previous();
    }

    @Step("Validate HealthIssue Content")
    public void Validate_HealthIssue_Content() throws SQLException, ClassNotFoundException {

            for (int i = 1; i <= jsonPath.getList("content.healthIssues").size(); i++) {
                int count=0;
                String val = Integer.toString(i - 1);
                String healthid=jsonPath.getString("content.healthIssues[" + val + "].id");
                String issue=jsonPath.getString("content.healthIssues[" + val + "].issue");

                get_db_data(healthid);
                while (results.next()) {
                    count++;
                    Assert.assertEquals(results.getString("id"), EncryptionServiceImpl.decryptToLong(healthid).toString());
                    Assert.assertEquals(results.getString("issue"), issue);

                }

                Assert.assertEquals("Data miss match API:DB",1,count);
        }
    }
}
