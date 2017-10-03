package com.aut;

import org.junit.Assert;

import java.sql.SQLException;

import static com.aut.DatabaseFactory.validateResultSet;

public class ElderAPIFactory extends BaseClass {


    public void setAPIValuesElder() {
        setElderId(getJsonPath().getString("content.elder.id"));
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
        setBasestationid(getJsonPath().getString("content.elder.baseStation.id"));
        setHealthIssueIdCount(getJsonPath().getList("content.elder.healthIssues").size());

    }

    public void get_Carer_Data_From_Carers_Array() {
        setCarerIdCount(getJsonPath().getList("content.carers").size());
        for (int carerCount = 1; carerCount <= getCarerIdCount(); carerCount++) {
            String val = Integer.toString(carerCount - 1);
            setCarerId(getJsonPath().getString("content.carers[" + val + "].id"));
            setFname(getJsonPath().getString("content.carers[" + val + "].firstName"));
            setLname(getJsonPath().getString("content.carers[" + val + "].lastName"));
            setEmail(getJsonPath().getString("content.carers[" + val + "].email"));
            setRoleId(getJsonPath().getString("content.carers[" + val + "].roleID"));
            setRoleName(getJsonPath().getString("content.carers[" + val + "].roleName"));
            setUsername(getJsonPath().getString("content.carers[" + val + "].username"));
        }
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
        if (getHealthIssueIdCount() >= 1) {
            for (int healthIssueCount = 1; healthIssueCount <= getHealthIssueIdCount(); healthIssueCount++) {
                String val = Integer.toString(healthIssueCount - 1);
                setHealthIssueId(getJsonPath().getString("content.elder.healthIssues[" + val + "].id"));
                setHealthIssueName(getJsonPath().getString("content.elder.healthIssues[" + val + "].issue"));
            }
        }
    }


    public void getElderDBDataById(String elderid) throws SQLException, ClassNotFoundException {
        if (!(getDeviceid() == null) && (getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId, grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if (!(getDeviceid() == null) && (getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id";
        } else if (!(getDeviceid() == null) && !(getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id join main.base_station on grampa.base_station_id=base_station.id";
        } else if ((getDeviceid() == null) && !(getHealthIssueIdCount() > 0)) {
            sql = "select person.id as id , person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id=" + elderid + " and address.person_id=" + elderid + " join main.grampa on grampa.id=" + elderid + " join main.city on address.city= city.id";
        }
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));

        if (!getResults().next()) {
            validateElderDetailsTableByTableById(elderid);

        } else {
            getResults().previous();
        }

    }
    public void getElderDBDataByEmail(String elderEmail) throws SQLException, ClassNotFoundException {

        if (!(getDeviceIdCount() == 0) && !(getHealthIssueIdCount() == 0)) {
            sql = "select person.id as id,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId, grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename from main.person join main.address on person.id= address.person_id and person.email='" + elderEmail + "' join main.grampa on grampa.id=person.id join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id join main.phone_number on phone_number.person_id=grampa.id";
        } else if ((getDeviceIdCount() == 0) && !(getHealthIssueIdCount() == 0)) {
            sql = "select person.id as id ,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa_health_issues.health_issue_id as healthissueid, health_issues.issue as healthissuename, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id= address.person_id and person.email='" + elderEmail + "' join main.grampa on grampa.id=person.id join main.city on address.city= city.id join main.grampa_health_issues on grampa_health_issues.grampa_id=grampa.id join main.health_issues on health_issues.id=grampa_health_issues.health_issue_id join main.phone_number on phone_number.person_id=grampa.id";
        } else if (!(getDeviceIdCount() == 0) && (getHealthIssueIdCount() == 0)) {
            sql = "select person.id as id ,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum,grampa.base_station_id as deviceid, base_station.device_key as devicekey, base_station.tv_brand_id as devicebrandid, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id= address.person_id and person.email='" + elderEmail + "' join main.grampa on grampa.id=person.id join main.base_station on grampa.base_station_id=base_station.id join main.city on address.city= city.id join main.phone_number on phone_number.person_id=grampa.id";
        } else if ((getDeviceIdCount() == 0) && (getHealthIssueIdCount() == 0)) {
            sql = "select person.id as id ,phone_number.phone_number as phonenumber, phone_number.phone_type as phonenumbertype,person.ethnicity_id as ethnicityid,person.religion_id as religionid, person.last_name as lname , person.first_name as fname, grampa.status as status, grampa.date_of_birth as dob, person.email as email, person.gender as gender, address.id as addressid, address.postal_code as postalcode, address.door_number as doornum, address.street as street, address.address_type as addresstype, address.city as cityId, city.country_id as cointryId from main.person join main.address on person.id= address.person_id and person.email='" + elderEmail + "' join main.grampa on grampa.id=person.id join main.city on address.city= city.id join main.phone_number on phone_number.person_id=grampa.id";
        }
        System.out.println("SQL: "+sql);
        setResults(DatabaseFactory.getDBData(sql));

        if (!getResults().next()) {
            validateElderDetailsTableByTableByEmail(elderEmail);

        } else {
            getResults().previous();
        }

    }
    public void getCarerDBDataAssignedToAElderById(String elderid) throws SQLException, ClassNotFoundException {
        sql = "Select person.id as id, person.first_name AS fname, person.last_name as lname, person.email as email, grampa_user.grampa_role_id as roleId, grampa_role.role_name as rolename, main.user.username as username from main.grampa_user join main.person on grampa_user.user_id=person.id and grampa_user.grampa_id=" + (elderid) + " join main.grampa_role on grampa_user.grampa_role_id=grampa_role.role_id join main.user on grampa_user.user_id=main.user.id";
        setResults(DatabaseFactory.getDBData(sql));
        System.out.println(sql);
        validateResultSet(sql);
    }
    public void validateElderDetailsTableByTableByEmail(String elderEmail) throws SQLException, ClassNotFoundException {
        validateResultSet("select * from main.person where person.email='" + elderEmail + "'");
        validateResultSet("select * from main.address where person_id='(select id from main.person where email='" + elderEmail + "')'");
        validateResultSet("select * from main.grampa where id='(select id from main.person where email='" + elderEmail + "')'");


        if (!(getDeviceIdCount() == 0)) {
            validateResultSet("select * from main.base_station where base_station.id= (select base_station_id from main.grampa where id=(select id from main.person where email='" + elderEmail + "'))");
              }
        if (!(getHealthIssueIdCount() == 0)) {

           validateResultSet("select * from main.health_issues where health_issues.id= (select health_issue_id from main.grampa_health_issues where grampa_id=(select id from main.person where email='" + elderEmail + "'))");
              }

    }

    public void validateElderDetailsTableByTableById(String elderid) throws SQLException, ClassNotFoundException {
        validateResultSet("select * from main.person where id=" + elderid + "");
        validateResultSet("select * from main.address where person_id=" + elderid + "");
        validateResultSet("select * from main.grampa where id=" + elderid + "");

        if (!(getDeviceid() == null)) {
            validateResultSet("select * from main.base_station where base_station.id= (select base_station_id from main.grampa where id=" + elderid + ")");
        }
        if ((getHealthIssueIdCount() > 0)) {
            validateResultSet("select * from main.health_issues where health_issues.id= (select health_issue_id from main.grampa_health_issues where grampa_id=" + elderid + ")");
        }
    }
    public void getElder_CarerAssignedStatusDB() throws SQLException, ClassNotFoundException {
        sql = "select count(*) as status from main.grampa_user where grampa_id=" + getElderId() + " and user_id=" + getCarerId() + " and grampa_role_id=" + getCarerRoleId() + "";
        validateResultSet(sql);
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
    }
    public void getEldersNotAssignedToDeviceDB() throws SQLException, ClassNotFoundException {
        sql = "select person.id as id , person.last_name as lname , person.first_name as fname,grampa.location_id as locationid from main.person join main.grampa on grampa.id=person.id and grampa.base_station_id is null";
        validateResultSet(sql);
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
    }

}
