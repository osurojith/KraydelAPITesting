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


    @Step("User enter HealthIssue API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></healthissues/partial>")
    public void User_enter_HealthIssue_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the HealthIssue API")
    public void User_call_the_HealthIssue_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate HealthIssue Content")
    public void Validate_HealthIssue_Content() throws SQLException, ClassNotFoundException {

            for (int i = 1; i <= jsonPath.getList("content.healthIssues").size(); i++) {
                String val = Integer.toString(i - 1);
                String healthid=jsonPath.getString("content.healthIssues[" + val + "].id");
                String issue=jsonPath.getString("content.healthIssues[" + val + "].issue");


                Assert.assertEquals(true, !healthid.isEmpty());
                Assert.assertEquals(true, !issue.isEmpty());

                String sqlhealthissue="select * from main.health_issues where id="+ EncryptionServiceImpl.decryptToLong(healthid)+" and issue='"+issue+"'";
                Assert.assertEquals("Validate HealthIssue id and Issue :"+sqlhealthissue,1, DBConn.getRowCount(sqlhealthissue));


        }
    }
}
