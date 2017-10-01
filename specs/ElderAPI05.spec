Update Elder API
=====================
Created by OsuraL on 9/1/2017


          |elderid|username      |password|logout_status|response_code|role_name|firstName       |lastName|email          |gender|ethnicityId|religionId|dateOfBirth|countryId|addressID|postalCode|doorNumber|street|cityId|addressType|locationId|status_code|version|status|phoneID|phoneNumber|phoneType|baseStationid|tvBrandId|elderstatus|healthIssueid|
          |-------|--------------|--------|-------------|-------------|---------|----------------|--------|---------------|------|-----------|----------|-----------|---------|---------|----------|----------|------|------|-----------|----------|-----------|-------|------|-------|-----------|---------|-------------|---------|-----------|-------------|
          |1    |darshana      |letmein |OK           |200          |null     |TestFirstUpdated|TestLast|test6@gmail.com|M     |1          |1         |2017-12-07 |1        |301      |10524     |456       |AAA   |1     |PRIMARY    |1         |20000      |v1     |ACTIVE|12     |0112654659 |PRIMARY  |1            |1        |ACTIVE     |0            |



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>

Update Elder API
----------------
* User Enter Update Elder API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/"<elderid>
 *User enter Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
 *User enter List: addresses Update Elder API <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>
 *User enter phoneNumber: Update Elder API <phoneID> <phoneNumber> <phoneType>
 *User enter healthIssues: Create Elder API <healthIssueid>
 *User enter baseStation: Update Elder API <baseStationid> <tvBrandId>
 * User Call Update Elder API
 * Validate Status Code <status_code>
 *Validate HTTP Response <response_code>
 * User gets data from kraydel database Update Elder API <baseStationid><healthIssueid><email>
 *User enter Elder Details Update Elder API validating <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
 *User enter List: addresses Update Elder API validating <postalCode> <doorNumber> <street> <cityId> <addressType>
 *User enter phoneNumber: Update Elder API validating <phoneNumber> <phoneType>
 *User enter healthIssues: Update Elder API validating <healthIssueid>
  *User enter baseStation: Update Elder API validating <baseStationid> <tvBrandId>



LogOut API
----------------
   *User logout from kraydel <logout_status>
