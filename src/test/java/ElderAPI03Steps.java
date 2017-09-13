import KraydelEncryption.EncryptionServiceImpl;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.DBConn;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class ElderAPI03Steps extends BaseClass {


    @Step("User enter Elder Search API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></elders/search>")
    public void implementation1(String part1, String version, String part2) {
        this.api = part1 + version + part2;
    }

    @Step("User call the Elder Search API")
    public void User_call_the_Search_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


    @Step("Validate Elder Search API Users")
    public void Validate_Search_API_Users() throws Exception {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.elders").size(); i++) {
                String val = Integer.toString(i - 1);
                String id=jsonPath.getString("content.elders[" + val + "].id");
                String lname=jsonPath.getString("content.elders[" + val + "].lastName");
                String fname=jsonPath.getString("content.elders[" + val + "].firstName");
                String status=jsonPath.getString("content.elders[" + val + "].status").replace("INACTIVE","3").replace("ACTIVE","1");;
                String dob=jsonPath.getString("content.elders[" + val + "].dateOfBirth");
                String email=jsonPath.getString("content.elders[" + val + "].email");
                String gender=jsonPath.getString("content.elders[" + val + "].gender");
                String deviceid=jsonPath.getString("content.elders[" + val + "].baseStation.id");
                String devicekey=jsonPath.getString("content.elders[" + val + "].baseStation.deviceKey");
                String devicebrandid=jsonPath.getString("content.elders[" + val + "].baseStation.tvBrandId");
                String locationid=jsonPath.getString("content.elders[" + val + "].location.id");



                Assert.assertEquals(true, !id.isEmpty());
                Assert.assertEquals(true, !lname.isEmpty());
                Assert.assertEquals(true, !fname.isEmpty());
                Assert.assertEquals(true, !status.isEmpty());
                Assert.assertEquals(true, !dob.isEmpty());
                Assert.assertEquals(true, !email.isEmpty());
                Assert.assertEquals(true, !gender.isEmpty());

                String sqlperson="select * from main.person where id="+ EncryptionServiceImpl.decryptToLong(id)+" and first_name='"+fname+"' and last_name='"+lname+"' and email='"+email+"' and gender='"+gender+"'";
                Assert.assertEquals("Validate PERSON table: "+sqlperson,1, DBConn.getRowCount(sqlperson));







                if((jsonPath.getString("content.elders[" + val + "].baseStation.id")==null)&&!(jsonPath.getString("content.elders[" + val + "].location.id")==null)){
                    String sqluser="select * from main.grampa where id="+EncryptionServiceImpl.decryptToLong(id)+" and status='"+status+"' and base_station_id IS "+EncryptionServiceImpl.decryptToLong(deviceid)+" and location_id="+EncryptionServiceImpl.decryptToLong(locationid)+" and date_of_birth='"+dob+"'";
                    Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));

                }
                if((jsonPath.getString("content.elders[" + val + "].location.id")==null)&&!(jsonPath.getString("content.elders[" + val + "].baseStation.id")==null)){
                    String sqluser="select * from main.grampa where id="+EncryptionServiceImpl.decryptToLong(id)+" and status='"+status+"' and base_station_id="+EncryptionServiceImpl.decryptToLong(deviceid)+" and location_id IS "+EncryptionServiceImpl.decryptToLong(locationid)+" and date_of_birth='"+dob+"'";
                    Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));

                }
                if((jsonPath.getString("content.elders[" + val + "].location.id")==null)&&(jsonPath.getString("content.elders[" + val + "].baseStation.id")==null)){
                    String sqluser="select * from main.grampa where id="+EncryptionServiceImpl.decryptToLong(id)+" and status='"+status+"' and base_station_id IS "+EncryptionServiceImpl.decryptToLong(deviceid)+" and location_id IS "+EncryptionServiceImpl.decryptToLong(locationid)+" and date_of_birth='"+dob+"'";
                    Assert.assertEquals("Validate USER table: "+sqluser,1,DBConn.getRowCount(sqluser));

                }
                if(!(jsonPath.getString("content.elders[" + val + "].baseStation.deviceKey")==null)){
                    Assert.assertEquals(true, !devicekey.isEmpty());
                }
                if(!(jsonPath.getString("content.elders[" + val + "].baseStation.tvBrandId")==null)){
                    Assert.assertEquals(true, !devicebrandid.isEmpty());
                }



                //Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].baseStation.id").isEmpty());
                //Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].baseStation.deviceKey").isEmpty());
                //Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].baseStation.tvBrandId").isEmpty());


                //Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].ethnicityId").isEmpty());
                //Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].religionId").isEmpty());
                //Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].ethnicityName").isEmpty());
                //Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].religionName").isEmpty());
                /* if(!(jsonPath.getString("content.elders[" + val + "].ethnicityId")==null)){
                    Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].ethnicityId").isEmpty());
                }
                if(!(jsonPath.getString("content.elders[" + val + "].religionId")==null)){
                    Assert.assertEquals(true, !jsonPath.getString("content.elders[" + val + "].religionId").isEmpty());
                }*/
            }
        }
    }

    @Step("Validate Elder Search API Pagination")
    public void Validate_Search_API_Pagination() {
        if (status_code.equals("20000")) {
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageNumber").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.pageSize").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalPages").isEmpty());
            Assert.assertEquals(true, !jsonPath.getString("pagination.totalRecords").isEmpty());
        }
    }


}
