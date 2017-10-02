Update Elder API
=====================


    |TestCaseId |elderid|username      |password|logout_status|response_code|status_code|version|firstName       |lastName|email          |gender|ethnicityId|religionId|dateOfBirth|countryId|addressID|postalCode|doorNumber|street|cityId|addressType|locationId|phoneID|phoneNumber|phoneType|baseStationid|tvBrandId|elderstatus|healthIssueid|
    |-----------|-------|--------------|--------|-------------|-------------|-----------|-------|----------------|--------|---------------|------|-----------|----------|-----------|---------|---------|----------|----------|------|------|-----------|----------|-------|-----------|---------|-------------|---------|-----------|-------------|
    |ELDER-14/15|1      |darshana      |letmein |OK           |200          |20000      |v1     |TestFirstUpdated|TestLast|test6@gmail.com|M     |1          |1         |2017-12-07 |1        |301      |10524     |456       |AAA   |1     |PRIMARY    |1         |12     |0112654659 |PRIMARY  |1            |1        |ACTIVE     |0            |
    |ELDER-16   |1      |apit@gmail.com|letmein |OK           |200          |40100      |v1     |TestFirstUpdated|TestLast|test6@gmail.com|M     |1          |1         |2017-12-07 |1        |301      |10524     |456       |AAA   |1     |PRIMARY    |1         |12     |0112654659 |PRIMARY  |1            |1        |ACTIVE     |0            |
    |ELDER-14/15|1      |darshana      |letmein |OK           |400          |40000      |v1     |TestFirstUpdated|TestLast|test6@gmail.com|M     |1          |1         |2017-12-07 |1        |301      |10524     |456       |AAA   |1     |PRIMARY    |1         |12     |0112654659 |PRIMARY  |1            |1        |ACTIVE     |0            |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Update Elder API
----------------
    * User Enter Update Elder API "/api/"<version>"/elders/"<elderid>
    * User enter Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
    * User enter List: addresses Update Elder API <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>
    * User enter phoneNumber: Update Elder API <phoneID> <phoneNumber> <phoneType>
    * User enter healthIssues: Create Elder API <healthIssueid>
    * User enter baseStation: Update Elder API <baseStationid> <tvBrandId>
    * User Call Update Elder API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database Update Elder API <baseStationid><healthIssueid><email>
    * Validate Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
    * Validate addresses Update Elder API <postalCode> <doorNumber> <street> <cityId> <addressType>
    * Validate phoneNumber: Update Elder API <phoneNumber> <phoneType>
    * Validate healthIssues: Update Elder API <healthIssueid>
    * Validate baseStation: Update Elder API <baseStationid> <tvBrandId>

LogOut API
----------------
    * User logout from kraydel <logout_status>
