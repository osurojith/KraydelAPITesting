
import com.aut.*;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static com.aut.DatabaseFactory.resetDB;

public class ElderAPI05Steps extends ElderAPIFactory {
    long elder_id;

    @Step("User Enter Update Elder API </api/><version></elders/><elderid>")
    public void Enter_API(String arg0, String arg1, String arg2, long arg3) throws Exception {
        setApi(arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3));

    }


    @Step("User enter Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>")
    public void Enter_user_details(String firstName, String lastName, String email, String gender, String ethnicityId, String religionId, String dateOfBirth, String locationId, String elderstatus) throws Exception {
        setFname(firstName);
        setLname(lastName);
        setEmail(email);
        setGender(gender);
        setEthnicityId(ethnicityId);
        setReligionId(religionId);
        setDob(dateOfBirth);
        setLocationid(locationId);
        setElderStatus(elderstatus);

       /* body = "{\n" +
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


    @Step("User enter List: addresses Update Elder API <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>")
    public void Enter_Address_Details(String countryId, String addressID, String postalCode, String doorNumber, String street, String cityId, String addressType) throws Exception {
        setCointryId(countryId);
        setCityId(cityId);
        setPostalCode(postalCode);
        setDoorNum(doorNumber);
        setStreet(street);
        setCityId(cityId);
        setAddressTypeRaw(addressType);
        setAddressType(addressType);
        //body = body + "\"addresses\":[{\"countryId\": \"" + EncryptionServiceImpl.encryptToString(countryId) + "\",\"id\": \"" + EncryptionServiceImpl.encryptToString(addressID) + "\",\"doorNumber\":\"" + doorNumber + "\",\"street\":\"" + street + "\",\"postalCode\":\"" + postalCode + "\",\"cityId\":\"" + EncryptionServiceImpl.encryptToString(cityId) + "\",\"addressType\":\"" + addressType + "\"}],";
    }


    @Step("User enter phoneNumber: Update Elder API <phoneID> <phoneNumber> <phoneType>")
    public void Enter_phoneNumber(String phoneID, String phoneNumber, String phoneType) throws Exception {
        setPhoneNumber(phoneNumber);
        setPhoneType(phoneType);
        setPhoneId(phoneID);
        //body = body + "\"phoneNumbers\":[{\"id\": \"" + EncryptionServiceImpl.encryptToString(phoneID) + "\",\"phoneNumber\":\"" + phoneNumber + "\",\"phoneType\":\"" + phoneType + "\"}]";
    }

    @Step("User enter baseStation: Update Elder API <baseStationid> <tvBrandId>")
    public void Enter_baseStation(String baseStationid, String tvBrandId) throws Exception {
        setDeviceid(baseStationid);
        setDevicebrandid(tvBrandId);
       /* if (!(baseStationid == 0))
            body = body + ",\"baseStation\":{\"id\":\"" + EncryptionServiceImpl.encryptToString(baseStationid) + "\",\"tvBrandId\":\"" + EncryptionServiceImpl.encryptToString(tvBrandId) + "\"";
        body = body + "}}";
        System.out.println("Body: " + body);*/
    }

    @Step("User enter healthIssues: Update Elder API <healthIssueid>")
    public void enter_HealthIssue_Id(String helthIssueId) {
        setHealthIssueId(helthIssueId);
    }

    @Step("User create user update method request body")
    public void createRequestBody() throws Exception {
        body = "{\n" +
                "\"firstName\":\"" + getFname() + "\",\n" +
                "\"lastName\":\"" + getLname() + "\",\n" +
                "\"email\":\"" + getEmail() + "\",\n" +
                "\"gender\":\"" + getGender() + "\",\n" +
                "\"ethnicityId\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getEthnicityId())) + "\",\n" +
                "\"religionId\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getReligionId())) + "\",\n" +
                "\"dateOfBirth\":\"" + getDob() + "\",\n" +
                "\"locationId\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getLocationid())) + "\",\n" +
                "\"status\":\"" + getElderStatus() + "\",\n" +
                "\"picture\":null,";
        body = body + "\"addresses\":[{\"countryId\": \"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getCointryId())) + "\",\"id\": \"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getAddressId())) + "\",\"doorNumber\":\"" + getDoorNum() + "\",\"street\":\"" + getStreet() + "\",\"postalCode\":\"" + getPostalCode() + "\",\"cityId\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getCityId())) + "\",\"addressType\":\"" + getAddressTypeRaw() + "\"}],";
        body = body + "\"phoneNumbers\":[{\"id\": \"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getPhoneId())) + "\",\"phoneNumber\":\"" + getPhoneNumber() + "\",\"phoneType\":\"" + getPhoneType() + "\"}]";
        if (!(getDeviceid().equalsIgnoreCase("0")))
            body = body + ",\"baseStation\":{\"id\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getDeviceid())) + "\",\"tvBrandId\":\"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getDevicebrandid())) + "\"";
        body = body + "}}";
        System.out.println("Body: " + body);
    }

    @Step("User Call Update Elder API")
    public void Call_create_user_API() {

        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.putMethodBody(this.api, header, body);
        setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database Update Elder API")
    public void get_Elder_DB_Data() throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        getElderDBDataByEmail(getEmail());
    }

    @Step("Validate Elder Details Update Elder API")
    public void elder_details_validating() throws SQLException, ClassNotFoundException {
        resetDB();
        while (getResults().next()) {
            Assert.assertEquals("Validate person.last_name", getResults().getString("lname"), getLname());
            Assert.assertEquals("Validate person.first_name", getResults().getString("fname"), getFname());
            Assert.assertEquals("Validate grampa.date_of_birth", getResults().getString("dob"), getDob());
            Assert.assertEquals("Validate person.email", getResults().getString("email"), getEmail());
            Assert.assertEquals("Validate person.gender", getResults().getString("gender"), getGender());
            Assert.assertEquals("Validate person.email", getResults().getString("ethnicityid"), getEthnicityId());
            Assert.assertEquals("Validate person.gender", getResults().getString("religionid"), getReligionId());
        }


    }


    @Step("Validate addresses Update Elder API")
    public void address_Details_validating() throws SQLException, ClassNotFoundException {
        resetDB();
        while (getResults().next()) {
            Assert.assertEquals("Validate address.postal_code", getResults().getString("postalcode"), getPostalCode());
            Assert.assertEquals("Validate address.door_number", getResults().getString("doornum"), getDoorNum());
            Assert.assertEquals("Validate address.street", getResults().getString("street"), getStreet());
            Assert.assertEquals("Validate address.address_type", getResults().getString("addresstype"), getAddressType());
            Assert.assertEquals("Validate address.city", getResults().getString("cityId"), getCityId());
        }

    }


    @Step("Validate phoneNumber: Update Elder API")
    public void phoneNumber_Validating() throws SQLException, ClassNotFoundException {
        resetDB();
        while (getResults().next()) {
            Assert.assertEquals("Validate phone_number.number", getResults().getString("phonenumber"), getPhoneNumber());
            Assert.assertEquals("Validate phone_number.type", getResults().getString("phonenumbertype"), getPhoneType());
        }

    }

    @Step("Validate healthIssues: Update Elder API")
    public void healthIssues_Validating() throws SQLException, ClassNotFoundException {

        if (!(getHealthIssueId().equalsIgnoreCase("0"))) {
            resetDB();
            while (getResults().next()) {
                Assert.assertEquals("Validate grampa_health_issues.health_issue_id", getResults().getString("healthissueid"), getHealthIssueId());
            }
        }

    }

    @Step("Validate baseStation: Update Elder API")
    public void baseStation_Validating() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            if (!(getDeviceid().equalsIgnoreCase("0"))) {
                resetDB();
                while (getResults().next()) {
                    Assert.assertEquals("Validate grampa.base_station_id", getResults().getString("deviceid"), getDeviceid());
                    Assert.assertEquals("Validate base_station.tv_brand_id", getResults().getString("devicebrandid"), getDevicebrandid());
                }
            }
        }
    }



}
