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

public class UserAPI07Steps extends BaseClass {


    @Step("User enter View Elders Assigned API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></user/elders>")
    public void User_enter_View_Elders_Assigned_API(String part1, String part2, String part3) {
        this.api = part1 + part2 + part3;
    }

    @Step("User call the View Elders Assigned API")
    public void User_call_the_View_Elders_Assigned_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }



    @Step("Validate View Elders Assigned Content")
    public void Validate_content() throws Exception {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.elders").size(); i++) {
                    String val = Integer.toString(i - 1);


                String id=jsonPath.getString("content.elders[" + val + "].id");
                String firstname=jsonPath.getString("content.elders[" + val + "].firstName");
                String lastname=jsonPath.getString("content.elders[" + val + "].lastName");
                String gender=jsonPath.getString("content.elders[" + val + "].gender");



                Assert.assertEquals(true, !id.isEmpty());
                Assert.assertEquals(true, !firstname.isEmpty());
                Assert.assertEquals(true, !lastname.isEmpty());
                Assert.assertEquals(true, !gender.isEmpty());

                String sqlperson="select * from main.person where id="+ EncryptionServiceImpl.decryptToLong(id)+" and first_name='"+firstname+"' and last_name='"+lastname+"' and gender='"+gender+"'";
                Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));


            }
        }
    }


}
