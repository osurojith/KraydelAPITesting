Create Elder API
=====================


    |TestCaseId |username      |password|logout_status|response_code|status_code|version|status|firstName         |lastName|email           |gender|ethnicityId|religionId|dateOfBirth|postalCode|doorNumber|street|cityId|addressType|locationId|phoneNumber|phoneType|healthIssueid|baseStationid|tvBrandId|baseStationstatus|elderstatus|
    |-----------|--------------|--------|-------------|-------------|-----------|-------|------|------------------|--------|----------------|------|-----------|----------|-----------|----------|----------|------|------|-----------|----------|-----------|---------|-------------|-------------|---------|-----------------|-----------|
    |ELDER-10/11|darshana      |letmein |OK           |200          |20000      |v1     |ACTIVE|TestFirstDUPLICATE|TestLast|test789@gmail.com|M     |1          |1         |2017-12-07 |10524     |456       |AAA   |1     |PRIMARY    |1         |0112654659 |PRIMARY  |0            |1            |1        |ASSIGNED         |ACTIVE     |
    //|ELDER-12   |apit@gmail.com|letmein |OK           |400          |40100      |v1     |ACTIVE|TestFirstDUPLICATE|TestLast|test88@gmail.com|M     |1          |1         |2017-12-07 |10524     |456       |AAA   |1     |PRIMARY    |1         |0112654659 |PRIMARY  |0            |0            |1        |ASSIGNED         |ACTIVE     |
    //|ELDER-13   |darshana      |letmein |OK           |400          |40100      |v1     |ACTIVE|TestFirstDUPLICATE|TestLast|test88@gmail.com|M     |1          |1         |2017-12-07 |10524     |456       |AAA   |1     |PRIMARY    |1         |0112654659 |PRIMARY  |0            |0            |1        |ASSIGNED         |ACTIVE     |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Create Elder API
----------------
    * User Enter Create Elder API "/api/"<version>"/elders"
    * User enter Elder Details Create Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
    * User enter List: addresses Create Elder API <postalCode> <doorNumber> <street> <cityId> <addressType>
    * User enter phoneNumber: Create Elder API <phoneNumber> <phoneType>
    * User enter healthIssues: Create Elder API <healthIssueid>
    * User enter baseStation: Create Elder API <baseStationid> <tvBrandId> <baseStationstatus>
    * User create method request body
    * User Call Create Elder API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database Create Elder API <email>
    * Validate Elder Details Create Elder API
    * Validate addresses Create Elder API
    * Validate phoneNumber: Create Elder API
    * Validate healthIssues: Create Elder API
    * Validate baseStation: Create Elder API

LogOut API
----------------
    * User logout from kraydel <logout_status>
