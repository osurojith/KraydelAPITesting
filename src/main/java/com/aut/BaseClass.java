package com.aut;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.ResultSet;

public class BaseClass {
    public String sql=null;
    public String api = null;
    public String body = null;
    private String username = null;
    public String password = null;
    public static String status_code=null;
    public static String token = null;
    public static  Response response;
    public static JsonPath jsonPath;
    public static ResultSet results;

    private String elderId;
    private String carerId;
    private int elderIdCount;
    private int carerIdCount;
    private String lname;
    private String fname;
    private String status;
    private String dob;
    private String email;
    private String gender;
    private String deviceId;
    private int deviceIdCount;
    private String deviceKey;
    private String devicebrandId;
    private String locationId;
    private String basestationId;
    private int healthIssueIdCount;
    private int addressIdCount;
    private String addressId;
    private String postalCode;
    private String doorNum;
    private String street;
    private String addressType;
    private String addressTypeRaw;
    private String cityId;
    private String cointryId;
    private String healthIssueId;
    private String healthIssueName;
    private String roleId;
    private String roleName;
    private String ethnicityId;
    private String religionId;
    private String elderStatus;
    private String phoneNumber;
    private String phoneType;
    private String deviceStatus;




    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = System.getenv("URI") +api;
        System.out.println("API: " + getApi());
    }

    public JsonPath getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(JsonPath jsonPath) {
        this.jsonPath = jsonPath;
    }

    public ResultSet getResults() {
        return results;
    }

    public void setResults(ResultSet results) {
        this.results = results;
    }


    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status.replace("INACTIVE", "3").replace("ACTIVE", "1");
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDeviceid() {
        return deviceId;
    }

    public void setDeviceid(String deviceid) {
        this.deviceId = deviceid;
    }

    public String getDevicekey() {
        return deviceKey;
    }

    public void setDevicekey(String devicekey) {
        this.deviceKey = devicekey;
    }

    public String getDevicebrandid() {
        return devicebrandId;
    }

    public void setDevicebrandid(String devicebrandid) {
        this.devicebrandId = devicebrandid;
    }

    public String getLocationid() {
        return locationId;
    }

    public void setLocationid(String locationid) {
        this.locationId = locationId;
    }

    public String getBasestationid() {
        return basestationId;
    }

    public void setBasestationid(String basestationId) {
        this.basestationId = basestationId;
    }

    public int getHealthIssueIdCount() {
        return healthIssueIdCount;
    }

    public void setHealthIssueIdCount(int healthIssueIdCount) {
        this.healthIssueIdCount = healthIssueIdCount;
    }
    public int getAddressIdCount() {
        return addressIdCount;
    }

    public void setAddressIdCount(int addressIdCount) {
        this.addressIdCount = addressIdCount;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDoorNum() {
        return doorNum;
    }

    public void setDoorNum(String doorNum) {
        this.doorNum = doorNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType.replace("PRIMARY", "1");
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCointryId() {
        return cointryId;
    }

    public void setCointryId(String cointryId) {
        this.cointryId = cointryId;
    }

    public String getHealthIssueId() {
        return healthIssueId;
    }

    public void setHealthIssueId(String healthIssueId) {
        this.healthIssueId = healthIssueId;
    }

    public String getHealthIssueName() {
        return healthIssueName;
    }

    public void setHealthIssueName(String healthIssueName) {
        this.healthIssueName = healthIssueName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getElderId() {
        return elderId;
    }

    public void setElderId(String elderId) {
        this.elderId = elderId;
    }

    public String getCarerId() {
        return carerId;
    }

    public void setCarerId(String carerId) {
        this.carerId = carerId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getElderIdCount() {
        return elderIdCount;
    }

    public void setElderIdCount(int elderIdCount) {
        this.elderIdCount = elderIdCount;
    }

    public int getCarerIdCount() {
        return carerIdCount;
    }

    public void setCarerIdCount(int carerIdCount) {
        this.carerIdCount = carerIdCount;
    }

    public int getDeviceIdCount() {
        return deviceIdCount;
    }

    public void setDeviceIdCount(int deviceIdCount) {
        this.deviceIdCount = deviceIdCount;
    }

    public String getEthnicityId() {
        return ethnicityId;
    }

    public void setEthnicityId(String ethnicityId) {
        this.ethnicityId = ethnicityId;
    }

    public String getReligionId() {
        return religionId;
    }

    public void setReligionId(String religionId) {
        this.religionId = religionId;
    }

    public String getElderStatus() {
        return elderStatus;
    }

    public void setElderStatus(String elderStatus) {
        this.elderStatus = elderStatus;
    }

    public String getAddressTypeRaw() {
        return addressTypeRaw;
    }

    public void setAddressTypeRaw(String addressTypeRaw) {
        this.addressTypeRaw = addressTypeRaw;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(String deviceStatus) {
        this.deviceStatus = deviceStatus;
    }
}
