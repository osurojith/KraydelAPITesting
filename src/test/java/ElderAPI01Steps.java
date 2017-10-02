
import com.aut.*;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ElderAPI01Steps extends ElderAPIFactory {


    @Step("User enter Elder Search API By ID </api/><version></elders/><elder-ID>")
    public void enter_API(String part1, String part2, String part3, long part4) throws Exception {
        setApi(part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4));

    }

    @Step("User call the Elder Search API By ID")
    public void call_The_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(getApi(), header);
        setJsonPath(new JsonPath(this.response.getBody().asString()));

    }
    @Step("User store API returned values Elder Search API By ID")
    public void store_API_Values() {
        setAPIValuesElder();
        setAPIValuesElderAddress();
        setAPIValuesHealthIssues();
    }

    @Step("User gets data from kraydel database Search API By ID <elder-ID>")
    public void get_Elder_Details_From_Database(String elderid) throws SQLException, ClassNotFoundException {
        getElderDBDataById(elderid);
    }


    public void resetDB() throws SQLException {
        while (getResults().previous()) {
            System.out.println();
        }
    }

    @Step("Validate Elder Search API By ID Users")
    public void Validate_Search_API_Users() throws SQLException, ClassNotFoundException {

            int count = 0;

            resetDB();
            while (getResults().next()) {
                count++;
                Assert.assertEquals("Validate person.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(getId()).toString());
                Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), getLname());
                Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), getFname());
                Assert.assertEquals("Validate person.status", getResults().getString("status"), getStatus());
                Assert.assertEquals("Validate grampa.date_of_birth", getResults().getString("dob"), getDob());
                Assert.assertEquals("Validate person.email", getResults().getString("email"), getEmail());
                Assert.assertEquals("Validate person.gender", getResults().getString("gender"), getGender());
            }
            Assert.assertEquals("Invalid data count", 1, count);

            if (!(getDeviceid() == null)) {
                resetDB();
                while (getResults().next()) {
                    Assert.assertEquals("Validate grampa.base_station_id", getResults().getString("deviceid"), EncryptionServiceImpl.decryptToLong(getDeviceid()).toString());
                    Assert.assertEquals("Validate base_station.device_key", getResults().getString("devicekey"), getDevicekey());
                    Assert.assertEquals("Validate base_station.tv_brand_id", getResults().getString("devicebrandid"), EncryptionServiceImpl.decryptToLong(getDevicebrandid()).toString());

                }
            }

    }


    @Step("Validate Elder Search API By ID Address")
    public void Validate_address() throws SQLException, ClassNotFoundException {

            int tableRow = 0;
            Assert.assertEquals("No elders found", true, getJsonPath().getList("content.elder.addresses").size() >= 1);
            resetDB();
            while (getResults().next()) {
                tableRow++;
                for (int i = 1; i <= getAddressIdCount(); i++) {
                    Assert.assertEquals("Validate address.id", getResults().getString("addressid"), EncryptionServiceImpl.decryptToLong(getAddressId()).toString());
                    Assert.assertEquals("Validate address.postal_code", getResults().getString("postalcode"), getPostalCode());
                    Assert.assertEquals("Validate address.door_number", getResults().getString("doornum"), getDoorNum());
                    Assert.assertEquals("Validate address.street", getResults().getString("street"), getStreet());
                    Assert.assertEquals("Validate address.address_type", getResults().getString("addresstype"), getAddressType());
                    Assert.assertEquals("Validate address.city", getResults().getString("cityId"), EncryptionServiceImpl.decryptToLong(getCityId()).toString());
                    Assert.assertEquals("Validate city.county_id", getResults().getString("cointryId"), EncryptionServiceImpl.decryptToLong(getCointryId()).toString());
                }


            }
            Assert.assertEquals("Data miss match API:DB", getAddressIdCount(), tableRow);

    }

    @Step("Validate Health Issues")
    public void Validate_health_issues() throws SQLException {

            if ((getHealthIssueIdCount() > 0)) {
                int tableRow = 0;
                resetDB();
                while (getResults().next()) {
                    tableRow++;
                    for (int healthIssueCount = 1; healthIssueCount <=getHealthIssueIdCount(); healthIssueCount++) {
                        Assert.assertEquals("Validate grampa_health_issues.health_issue_id", getResults().getString("healthissueid"), EncryptionServiceImpl.decryptToLong(getHealthIssueId()).toString());
                        Assert.assertEquals("Validate health_issues.issue", getResults().getString("healthissuename"), getHealthIssueName());
                    }
                }

                Assert.assertEquals("Data miss match API:DB", getHealthIssueIdCount(), tableRow);
            }
        }




}
