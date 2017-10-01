Create Elder API
=====================
Created by OsuraL on 9/1/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.


         |username|password|logout_status|response_code|role_name|firstName         |lastName|email           |gender|ethnicityId|religionId|dateOfBirth|postalCode|doorNumber|street|cityId|addressType|locationId|status_code|version|status|phoneNumber|phoneType|healthIssueid|baseStationid|tvBrandId|baseStationstatus|elderstatus|
         |--------|--------|-------------|-------------|---------|------------------|--------|----------------|------|-----------|----------|-----------|----------|----------|------|------|-----------|----------|-----------|-------|------|-----------|---------|-------------|-------------|---------|-----------------|-----------|
         |apit@gmail.com|letmein |OK           |200          |null     |TestFirstDUPLICATE|TestLast|test88@gmail.com|M     |1          |1         |2017-12-07 |10524     |456       |AAA   |1     |PRIMARY    |1         |20000      |v1     |ACTIVE|0112654659 |PRIMARY  |0            |0            |1        |ASSIGNED         |ACTIVE     |


LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>


Create Elder API
----------------
* User Enter Create Elder API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders"
 *User enter Elder Details Create Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
 *User enter List: addresses Create Elder API <postalCode> <doorNumber> <street> <cityId> <addressType>
 *User enter phoneNumber: Create Elder API <phoneNumber> <phoneType>
 *User enter healthIssues: Create Elder API <healthIssueid>
  *User enter baseStation: Create Elder API <baseStationid> <tvBrandId> <baseStationstatus>
 * User Call Create Elder API
 * Validate Status Code <status_code>
 * Validate HTTP Response <response_code>
 * User gets data from kraydel database Create Elder API <baseStationid><healthIssueid><email>
*User enter Elder Details Create Elder API validating <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
 *User enter List: addresses Create Elder API validating <postalCode> <doorNumber> <street> <cityId> <addressType>
 *User enter phoneNumber: Create Elder API validating <phoneNumber> <phoneType>
 *User enter healthIssues: Create Elder API validating <healthIssueid>
  *User enter baseStation: Create Elder API validating <baseStationid> <tvBrandId> <baseStationstatus>


LogOut API
----------------
   *User logout from kraydel <logout_status>
