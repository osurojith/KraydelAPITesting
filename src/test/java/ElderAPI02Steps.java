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

public class ElderAPI02Steps extends BaseClass {

    @Step("User enter Elder Search API view carers <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/><elder-ID></carers>")
    public void Enter_API(String arg0, String arg1, String arg2, String arg3, String arg4) {
        this.api = arg0 + arg1 + arg2 + arg3 + arg4;
    }

    @Step("User call the Elder Search API view carers")
    public void Call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Elder Search API view carers Content <elder-ID>")
    public void Validate_content(String elderId) throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.carers").size(); i++) {
                String val = Integer.toString(i - 1);

                String id=jsonPath.getString("content.carers[" + val + "].id");
                String fname=jsonPath.getString("content.carers[" + val + "].firstName");
                String lname=jsonPath.getString("content.carers[" + val + "].lastName");
                String email=jsonPath.getString("content.carers[" + val + "].email");
                int roleid=jsonPath.getInt("content.carers[" + val + "].roleID");
                String rolename=jsonPath.getString("content.carers[" + val + "].roleName");
                String username=jsonPath.getString("content.carers[" + val + "].username");


                Assert.assertEquals(true, !id.isEmpty());
                Assert.assertEquals(true, !fname.isEmpty());
                Assert.assertEquals(true, !lname.isEmpty());
                Assert.assertEquals(true, !email.isEmpty());
                //Assert.assertEquals(true, !roleid.isEmpty());
                Assert.assertEquals(true, !rolename.isEmpty());
                Assert.assertEquals(true, !username.isEmpty());


                String sqlperson="select * from main.person where id="+ EncryptionServiceImpl.decryptToLong(id)+" and first_name='"+fname+"' and last_name='"+lname+"' and email='"+email+"'";
                Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));

                String sqluser="select * from main.user where id="+EncryptionServiceImpl.decryptToLong(id)+" and username='"+username+"'";
                Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));


                String sqlgramparole="select * from main.grampa_user where grampa_id='"+EncryptionServiceImpl.decryptToLong(elderId)+"' and user_id='"+EncryptionServiceImpl.decryptToLong(id)+"' and grampa_role_id="+roleid+"";
                Assert.assertEquals("Validate grampa_user table: "+sqlgramparole,1,DBConn.getRowCount(sqlgramparole));
            }
        }
    }
}
