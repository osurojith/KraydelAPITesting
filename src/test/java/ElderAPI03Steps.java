import com.aut.ElderAPIFactory;
import com.aut.EncryptionServiceImpl;
import com.aut.HttpMethodsFactory;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ElderAPI03Steps extends ElderAPIFactory {

    int totalRecords = 0;

    @Step("User enter Elder Search API </api/><version></elders/search>")
    public void implementation1(String part1, String version, String part2) {
        setApi(part1 + version + part2);
    }

    @Step("User call the Elder Search API")
    public void user_call_the_Search_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        setJsonPath(new JsonPath(this.response.getBody().asString()));
    }


    @Step("Get data from database and Validate With Elder Search API Elder Details")
    public void validate_Search_API_Elder_Details_With_DB() throws Exception {
        setElderIdCount(getJsonPath().getList("content.elders").size());
        Assert.assertEquals("No elders found", true, getElderIdCount() >= 1);

        for (int elderCount = 1; elderCount <= getElderIdCount(); elderCount++) {

            String val = Integer.toString(elderCount - 1);

            setElderId(getJsonPath().getString("content.elders[" + val + "].id"));
            setLname(getJsonPath().getString("content.elders[" + val + "].lastName"));
            setFname(getJsonPath().getString("content.elders[" + val + "].firstName"));
            setStatus(getJsonPath().getString("content.elders[" + val + "].status"));
            setDob(getJsonPath().getString("content.elders[" + val + "].dateOfBirth"));
            setEmail(getJsonPath().getString("content.elders[" + val + "].email"));
            setGender(getJsonPath().getString("content.elders[" + val + "].gender"));
            setBasestationid(getJsonPath().getString("content.elders[" + val + "].baseStation.id"));
            setDevicekey(getJsonPath().getString("content.elders[" + val + "].baseStation.deviceKey"));
            setDevicebrandid(getJsonPath().getString("content.elders[" + val + "].baseStation.tvBrandId"));
            setLocationid(getJsonPath().getString("content.elders[" + val + "].location.id"));
            setHealthIssueIdCount(0);

            getElderDBDataById(EncryptionServiceImpl.decryptToLong(getElderId()).toString()); //get elder details from the datebase

            while (getResults().next()) {
                totalRecords++;
                Assert.assertEquals("Validate person.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(getElderId()).toString());
                Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), getLname());
                Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), getFname());
                Assert.assertEquals("Validate person.status", getResults().getString("status"), getStatus());
                Assert.assertEquals("Validate grampa.date_of_birth", getResults().getString("dob"), getDob());
                Assert.assertEquals("Validate person.email", getResults().getString("email"), getEmail());
                Assert.assertEquals("Validate person.gender", getResults().getString("gender"), getGender());

                if (!(getDeviceid() == null)) {
                    Assert.assertEquals("Validate grampa.base_station_id", getResults().getString("deviceid"), EncryptionServiceImpl.decryptToLong(getDeviceid()).toString());
                    Assert.assertEquals("Validate base_station.device_key", getResults().getString("devicekey"), getDevicekey());
                    Assert.assertEquals("Validate base_station.tv_brand_id", getResults().getString("devicebrandid"), EncryptionServiceImpl.decryptToLong(getDevicebrandid()).toString());

                }
            }


        }
    }




    @Step("Validate Elder Search API Pagination")
    public void Validate_Search_API_Pagination() {

            Assert.assertEquals(true, !getJsonPath().getString("pagination.pageNumber").isEmpty());
            Assert.assertEquals(true, !getJsonPath().getString("pagination.pageSize").isEmpty());
            Assert.assertEquals(true, !getJsonPath().getString("pagination.totalPages").isEmpty());
            Assert.assertEquals("Data Missmatch:", Integer.toString(totalRecords), getJsonPath().getString("pagination.totalRecords"));

    }


}
