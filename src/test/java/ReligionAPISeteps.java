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

public class ReligionAPISeteps extends BaseClass {


    @Step("User enter Religions API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></religions/partial>")
    public void User_enter_Religions_API(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Religions API")
    public void User_call_the_Religions_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql="select * from main.religion where id="+ EncryptionServiceImpl.decryptToLong(id)+"";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found  main.religion. ID:"+EncryptionServiceImpl.decryptToLong(id), true, results.next());
        results.previous();
    }


    @Step("Validate Religions Content")
    public void Validate_Religions_Content() throws SQLException, ClassNotFoundException {

            for (int i = 1; i <= jsonPath.getList("content.religions").size(); i++) {
                int count=0;
                String val = Integer.toString(i - 1);
                String religionid=jsonPath.getString("content.religions[" + val + "].id");
                String religionname=jsonPath.getString("content.religions[" + val + "].name");

                get_db_data(religionid);
                while (results.next()) {
                    count++;
                    Assert.assertEquals(results.getString("id"), EncryptionServiceImpl.decryptToLong(religionid).toString());
                    Assert.assertEquals(results.getString("name"), religionname);

                }

                Assert.assertEquals("Data miss match API:DB",1,count);




        }
    }





}
