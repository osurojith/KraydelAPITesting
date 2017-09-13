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

public class UserAPI02Steps extends BaseClass {
    @Step("User enter Search API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></users/search>")
    public void implementation1(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Search API")
    public void User_call_the_Search_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Search API Users")
    public void Validate_Search_API_Users() throws Exception {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.users").size(); i++) {
                String val = Integer.toString(i - 1);
                String id=jsonPath.getString("content.users[" + val + "].id");
                String username=jsonPath.getString("content.users[" + val + "].username");
                String lname=jsonPath.getString("content.users[" + val + "].lastName");
                String fname=jsonPath.getString("content.users[" + val + "].firstName");
                String email=jsonPath.getString("content.users[" + val + "].email");
                String gender=jsonPath.getString("content.users[" + val + "].gender");
                String status=jsonPath.getString("content.users[" + val + "].status").replace("INACTIVE","3").replace("ACTIVE","1");


                Assert.assertEquals(true, !id.isEmpty());
                Assert.assertEquals(true, !username.isEmpty());
                Assert.assertEquals(true, !lname.isEmpty());
                Assert.assertEquals(true, !fname.isEmpty());
                Assert.assertEquals(true, !email.isEmpty());
                Assert.assertEquals(true, !gender.isEmpty());
                Assert.assertEquals(true, !status.isEmpty());


                String sqlperson="select * from main.person where id="+ EncryptionServiceImpl.decryptToLong(id)+" and first_name='"+fname+"' and last_name='"+lname+"' and email='"+email+"' and gender='"+gender+"'";
                Assert.assertEquals("Validate PERSON table: "+sqlperson,1,DBConn.getRowCount(sqlperson));


                String sqluser="select * from main.user where id="+EncryptionServiceImpl.decryptToLong(id)+" and username='"+username+"' and status='"+status+"'";
                Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));


                for (int x = 1; i <= jsonPath.getList("content.users[" + val + "].roleNames").size(); i++) {
                    String val1 = Integer.toString(x - 1);
                    String roleName=jsonPath.getString("content.users[" + val + "].roleNames[" + val1 + "]");


                    Assert.assertEquals(true, !roleName.isEmpty());


                    String sqlrole="select * from main.role where role_name='"+roleName+"'";
                    String sqluserrole="select * from main.user_role where user_id="+EncryptionServiceImpl.decryptToLong(id)+" and role_id='"+DBConn.getValueInt(sqlrole,"id")+"'";


                    Assert.assertEquals("Validate User_role table: "+sqluserrole,1,DBConn.getRowCount(sqluserrole));

                }
            }
        }
    }

    @Step("Validate Search API Pagination")
    public void Validate_Search_API_Pagination() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageNumber").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageSize").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalPages").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalRecords").isEmpty());
        }
    }

}
