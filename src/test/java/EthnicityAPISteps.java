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


    @Step("User enter Ethnicity API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></ethnicities/partial>")
    public void implementation1(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }


    @Step("User call the Ethnicity API")
    public void User_call_the_Ethnicity_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Ethnicity Content")
    public void Validate_Ethnicity_Content() throws SQLException, ClassNotFoundException {

            for (int i = 1; i <= jsonPath.getList("content.ethnicities").size(); i++) {
                String val = Integer.toString(i - 1);
                String ethnicityid=jsonPath.getString("content.ethnicities[" + val + "].id");
                String ethnicityname=jsonPath.getString("content.ethnicities[" + val + "].name");

                Assert.assertEquals(true, !ethnicityid.isEmpty());
                Assert.assertEquals(true, !ethnicityname.isEmpty());

                String sqlethnicity="select * from main.ethnicity where id="+ EncryptionServiceImpl.decryptToLong(ethnicityid)+" and name='"+ethnicityname+"'";
                Assert.assertEquals("Validate Ethanacity id and name :"+sqlethnicity,1, DBConn.getRowCount(sqlethnicity));


        }
    }


}
