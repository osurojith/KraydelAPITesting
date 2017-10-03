import com.aut.ElderAPIFactory;
import com.aut.EncryptionServiceImpl;
import com.aut.HttpMethodsFactory;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ElderAPI04Steps extends ElderAPIFactory {
    @Step("User Enter Create Elder API </api/><version></elders>")
    public void enter_API(String part1, String version, String part2) {
        setApi(part1 + version + part2);
    }

    @Step("User enter Elder Details Create Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void enter_User_Details(String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) throws Exception {
        setFname(firstName);
        setLname(lastName);
        setEmail(email);
        setGender(gender);
        setEthnicityId(ethnicityId);
        setReligionId(religionId);
        setDob(dateOfBirth);
        setLocationid(locationId);
        setElderStatus(elderstatus);
        /*body = "{\n" +
                "\"firstName\":\"" + firstName + "\",\n" +
                "\"lastName\":\"" + lastName + "\",\n" +
                "\"email\":\"" + email + "\",\n" +
                "\"gender\":\"" + gender + "\",\n" +
                "\"ethnicityId\":\"" + EncryptionServiceImpl.encryptToString(ethnicityId) + "\",\n" +
                "\"religionId\":\"" + EncryptionServiceImpl.encryptToString(religionId) + "\",\n" +
                "\"dateOfBirth\":\"" + dateOfBirth + "\",\n" +
                "\"locationId\":\"" + EncryptionServiceImpl.encryptToString(locationId) + "\",\n" +
                "\"status\":\"" + elderstatus + "\",\n" +
                "\"picture\":null,";*/
    }


    @Step("User enter List: addresses Create Elder API <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void enter_Address_Details(String postalCode, String doorNumber, String street, String cityId, String addressType) throws Exception {
        // body = body + "\"addresses\":[{\"doorNumber\":\"" + doorNumber + "\",\"street\":\"" + street + "\",\"postalCode\":\"" + postalCode + "\",\"cityId\":\"" + EncryptionServiceImpl.encryptToString(cityId) + "\",\"addressType\":\"" + addressType + "\"}],";
        setPostalCode(postalCode);
        setDoorNum(doorNumber);
        setStreet(street);
        setCityId(cityId);
        setAddressTypeRaw(addressType);
    }


    @Step("User enter phoneNumber: Create Elder API <phoneNumber> <phoneType>")
    public void enter_PhoneNumber(String phoneNumber, String phoneType) {
        //body = body + "\"phoneNumbers\":[{\"phoneNumber\":\"" + phoneNumber + "\",\"phoneType\":\"" + phoneType + "\"}]";
        setPhoneNumber(phoneNumber);
        setPhoneType(phoneType);
    }

    @Step("User enter healthIssues: Create Elder API <healthIssueid>")
    public void enter_HealthIssuesDetails(long healthIssueId) throws Exception {
        setHealthIssueIdCount((int) healthIssueId);
        if (!(healthIssueId == 0))
            setHealthIssueId(Long.toString(healthIssueId));
        // body = body + ",\"healthIssues\":[{\"id\":\"" + EncryptionServiceImpl.encryptToString(healthIssueId) + "\"}]";
    }

    @Step("User enter baseStation: Create Elder API <baseStationid> <tvBrandId> <baseStationstatus>")
    public void enter_BaseStationDetails(long baseStationId, long tvBrandId, String baseStationStatus) throws Exception {
        setDeviceIdCount((int) baseStationId);
        if (!(baseStationId == 0)) {
            setDeviceid(Long.toString(baseStationId));
            setDevicebrandid(Long.toString(tvBrandId));
            setDeviceStatus(baseStationStatus);
        }
        //body = body + ",\"baseStation\":{\"id\":\"" + EncryptionServiceImpl.encryptToString(baseStationId) + "\",\"tvBrandId\":\"" + EncryptionServiceImpl.encryptToString(tvBrandId) + "\",\"status\":\"" + baseStationStatus + "\"";
        // body = body + "}}";
        //  System.out.println("Body: " + body);
    }

    @Step("User create method request body")
    public void createMethodBody() throws Exception {
        body = "{\n" +
                "\"firstName\":\""+getFname()+"\",\n" +
                "\"lastName\":\""+getLname()+"\",\n" +
                "\"email\":\""+getEmail()+"\",\n" +
                "\"gender\":\""+getGender()+"\",\n" +
                "\"ethnicityId\":\""+EncryptionServiceImpl.encryptToString(Long.parseLong(getEthnicityId()))+"\",\n" +
                "\"religionId\":\""+EncryptionServiceImpl.encryptToString(Long.parseLong(getReligionId()))+"\",\n" +
                "\"dateOfBirth\":\""+getDob()+"\",\n" +
                "\"locationId\":\""+EncryptionServiceImpl.encryptToString(Long.parseLong(getLocationid()))+"\",\n" +
                "\"status\":\""+getDeviceStatus()+"\",\n" +
                "\"picture\":null,\"addresses\":[{\"doorNumber\":\""+getDoorNum()+"\",\"street\":\""+getStreet()+"\",\"postalCode\":\""+getPostalCode()+"\",\"cityId\":\""+EncryptionServiceImpl.encryptToString(Long.parseLong(getCityId()))+"\",\"addressType\":\""+getAddressType()+"\"}],\"phoneNumbers\":[{\"phoneNumber\":\""+getPhoneNumber()+"\",\"phoneType\":\""+getPhoneType()+"\"}]}}\n";
        if (!(getHealthIssueId().equalsIgnoreCase("0")))
            body = body + ",\"healthIssues\":[{\"id\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getHealthIssueId())) + "\"}]";
        if (!(getDeviceid().equalsIgnoreCase("0")))
            body = body + ",\"baseStation\":{\"id\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getDeviceid())) + "\",\"tvBrandId\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getDevicebrandid())) + "\",\"status\":\"" + getDeviceStatus() + "\"";
            body = body + "}}";

        System.out.println("Body: " + body);
    }

    @Step("User Call Create Elder API")
    public void call_Create_User_API() {

        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.postMethodBody(this.api, header, body);
        setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database Create Elder API <baseStationid><healthIssueid><email>")
    public void get_ElderDB_Data(int basestationid, int healthissueid, String email) throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        getElderDBDataByEmail(email);
    }

    @Step("Validate Elder Details Create Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void enter_User_Details_Validating(String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) throws SQLException, ClassNotFoundException {

        while (getResults().next()) {
            Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), lastName);
            Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), firstName);
            Assert.assertEquals("Validate grampa.date_of_birth", getResults().getString("dob"), dateOfBirth);
            Assert.assertEquals("Validate person.email", getResults().getString("email"), email);
            Assert.assertEquals("Validate person.gender", getResults().getString("gender"), gender);
            Assert.assertEquals("Validate person.email", getResults().getString("ethnicityid"), (ethnicityId));
            Assert.assertEquals("Validate person.gender", getResults().getString("religionid"), (religionId));
        }
    }


    @Step("Validate addresses Create Elder API <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void enter_Address_Details_validating(String postalCode, String doorNumber, String street, String cityId, String addressType) throws SQLException, ClassNotFoundException {
        while (getResults().previous()) {

            Assert.assertEquals("Validate address.postal_code", getResults().getString("postalcode"), postalCode);
            Assert.assertEquals("Validate address.door_number", getResults().getString("doornum"), doorNumber);
            Assert.assertEquals("Validate address.street", getResults().getString("street"), street);
            Assert.assertEquals("Validate address.address_type", getResults().getString("addresstype"), addressType.replace("PRIMARY", "1"));
            Assert.assertEquals("Validate address.city", getResults().getString("cityId"), (cityId));
        }

    }


    @Step("Validate phoneNumber: Create Elder API <phoneNumber> <phoneType>")
    public void enter_PhoneNumber_Validating(String phoneNumber, String phoneType) throws SQLException, ClassNotFoundException {

        while (getResults().next()) {

            Assert.assertEquals("Validate phone_number.number", getResults().getString("phonenumber"), phoneNumber);
            Assert.assertEquals("Validate phone_number.type", getResults().getString("phonenumbertype"), phoneType);
        }

    }

    @Step("Validate healthIssues: Create Elder API <healthIssueid>")
    public void enter_HealthIssues_validating(String healthIssueid) throws SQLException, ClassNotFoundException {

        if (!(healthIssueid.equalsIgnoreCase("0"))) {//check health issue availability
            while (getResults().previous()) {
                Assert.assertEquals("Validate grampa_health_issues.health_issue_id", getResults().getString("healthissueid"), (healthIssueid));
            }
        }

    }

    @Step("Validate baseStation: Create Elder API <baseStationid> <tvBrandId> <baseStationstatus>")
    public void enter_BaseStation_Validating(String baseStationid, String tvBrandId, String baseStationstatus) throws SQLException, ClassNotFoundException {

        if (!(baseStationid.equalsIgnoreCase("0"))) {
            while (getResults().next()) {

                Assert.assertEquals("Validate grampa.base_station_id", getResults().getString("deviceid"), (baseStationid));
                Assert.assertEquals("Validate base_station.tv_brand_id", getResults().getString("devicebrandid"), (tvBrandId));
            }
        }

    }


}
