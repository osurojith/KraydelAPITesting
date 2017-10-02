Create User API
=====================


    |TestCaseId|username      |password|usernameC     |passwordC|firstname|lastname|email            |status|gender|postalCode|doorNumber|street|cityId|addressType|locationId|roleId|status_code|version|logout_status|response_code|
    |----------|--------------|--------|--------------|---------|---------|--------|-----------------|------|------|----------|----------|------|------|-----------|----------|------|-----------|-------|-------------|-------------|
    |USER-08/09|darshana      |letmein |TestingAPI003 |Test@12  |Testfirst|Testlast|test001@gmail.com|ACTIVE|M     |10524     |456       |AAA   |1     |PRIMARY    |1         |1     |20000      |v1     |OK           |201          |
    |USER-10   |apit@gmail.com|letmein |TestingAPI003 |Test@12  |Testfirst|Testlast|test001@gmail.com|ACTIVE|M     |10524     |456       |AAA   |1     |PRIMARY    |1         |1     |40100      |v1     |OK           |400          |
    |USER-11   |darshana      |letmein |apit@gmail.com|Test@12  |Testfirst|Testlast|test001@gmail.com|ACTIVE|M     |10524     |456       |AAA   |1     |PRIMARY    |1         |1     |20000      |v1     |OK           |201          |
    |USER-12   |darshana      |letmein |TestingAPI004 |Test@12  |Testfirst|Testlast|test001@gmail.com|ACTIVE|M     |10524     |456       |AAA   |1     |PRIMARY    |1         |1     |20000      |v1     |OK           |201          |
 
    

LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Create User API
----------------
    * User Enter Create User API "/api/"<version>"/users"
    * User enter User Details Create User API <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>
    * User enter List: addresses Create User API <postalCode> <doorNumber> <street> <cityId> <addressType>
    * User enter locations: id Create User API <locationId>
    * User enter roles: Create User API <roleId>
    * User Call Create User API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database Create User API <email>
    * Validate User Details Create User API <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>
    * Validate List: addresses Create User API <postalCode> <doorNumber> <street> <cityId> <addressType>
    * Validate locations: id Create User API <locationId>
    * Validate roles: Create User API <roleId>
    
LogOut API
----------------
    * User logout from kraydel <logout_status>
