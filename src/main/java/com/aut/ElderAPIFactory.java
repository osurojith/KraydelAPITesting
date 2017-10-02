package com.aut;

import org.junit.Assert;

import java.sql.SQLException;

import static com.aut.DatabaseFactory.validateResultSet;

public class ElderAPIFactory extends BaseClass {
    private String id;
    private String lname;
    private String fname;
    private String status;
    private String dob;
    private String email;
    private String gender;
    private String deviceId;
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
    private String cityId;
    private String cointryId;
    private String healthIssueId;
    private String healthIssueName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setAPIValuesElder() {
        setId(getJsonPath().getString("content.elder.id"));
        setLname(getJsonPath().getString("content.elder.lastName"));
        setFname(getJsonPath().getString("content.elder.firstName"));
        setStatus(getJsonPath().getString("content.elder.status"));
        setDob(getJsonPath().getString("content.elder.dateOfBirth"));
        setEmail(getJsonPath().getString("content.elder.email"));
        setGender(getJsonPath().getString("content.elder.gender"));
        setDeviceid(getJsonPath().getString("content.elder.baseStation.id"));
        setDevicekey(getJsonPath().getString("content.elder.baseStation.deviceKey"));
        setDevicebrandid(getJsonPath().getString("content.elder.baseStation.tvBrandId"));
        setLocationid(getJsonPath().getString("content.elder.locationId"));
        setBasestationid(getJsonPath().getString("content.elder.baseStation.id"));;
        setHealthIssueIdCount(getJsonPath().getList("content.elder.healthIssues").size());

    }

    public void setAPIValuesElderAddress() {
        setAddressIdCount(getJsonPath().getList("content.elder.addresses").size());
        for (int addressCount = 1; addressCount <= getAddressIdCount(); addressCount++) {
            String val = Integer.toString(addressCount - 1);
            setAddressId(getJsonPath().getString("content.elder.addresses[" + val + "].id"));
            setPostalCode(getJsonPath().getString("content.elder.addresses[" + val + "].postalCode"));
            setDoorNum(getJsonPath().getString("content.elder.addresses[" + val + "].doorNumber"));
            setStreet(getJsonPath().getString("content.elder.addresses[" + val + "].street"));
            setAddressType(getJsonPath().getString("content.elder.addresses[" + val + "].addressType"));
            setCityId(getJsonPath().getString("content.elder.addresses[" + val + "].cityId"));
            setCointryId(getJsonPath().getString("content.elder.addresses[" + val + "].countryId"));
        }
        }
    public void setAPIValuesHealthIssues() {
        setAddressIdCount(getJsonPath().getList("content.elder.addresses").size());
        if(getHealthIssueIdCount()>=1) {
            for (int healthIssueCount = 1; healthIssueCount <= getHealthIssueIdCount(); healthIssueCount++) {
                String val = Integer.toString(healthIssueCount - 1);
                setHealthIssueId(getJsonPath().getString("content.elder.healthIssues[" + val + "].id"));
                setHealthIssueName(getJsonPath().getString("content.elder.healthIssues[" + val + "].issue"));
            }
        }
    }


    public void getElderDBDataById(String elderid) throws SQLException, ClassNotFoundException {
        if (!(getBasestationid() == null) && (getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId, grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if (!(getBasestationid() == null) && (getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if (!(getBasestationid() == null) && !(getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id join main.base_station on grampa.base_station_id=base_station.id";
        } else if ((getBasestationid() == null) && !(getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id";
        }
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));

        if (!getResults().next()) {
            validateElderDetailsTableByTable(elderid);

        } else {
            getResults().previous();
        }

    }
    public void validateElderDetailsTableByTable(String elderid) throws SQLException, ClassNotFoundException {
        validateResultSet("select * from main.person where id=" + elderid + "");
        validateResultSet("select * from main.address where person_id=" + elderid + "");
        validateResultSet("select * from main.grampa where id=" + elderid + "");

        if (!(getBasestationid() == null)) {
            validateResultSet("select * from main.base_station where base_station.id= (select base_station_id from main.grampa where id=" + elderid + ")");
        }
        if ((getHealthIssueIdCount() > 0)) {
            sql = "";
            validateResultSet("select * from main.health_issues where health_issues.id= (select health_issue_id from main.grampa_health_issues where grampa_id=" + elderid + ")");
        }
    }


}
