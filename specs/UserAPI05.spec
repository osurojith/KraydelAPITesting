Update User API
=====================


    |TestCaseId|userid|username      |password|usernameC   |passwordC|firstname         |lastname|email           |status|gender|postalCode|doorNumber|street|cityId|addressType|locationId|roleId|status_code|version|addressid|logout_status|response_code|
    |----------|------|--------------|--------|------------|---------|------------------|--------|----------------|------|------|----------|----------|------|------|-----------|----------|------|-----------|-------|---------|-------------|-------------|
    |USER-13/14|5     |darshana      |letmein |TestingAPI01|Test@12  |TestAccountUpdated|Testlast|test01@gmail.com|ACTIVE|M     |10524     |456       |FFF   |1     |PRIMARY    |1         |1     |20000      |v1     |397      |OK           |200          |
    |USER-15   |5     |apit@gmail.com|letmein |TestingAPI01|Test@12  |TestAccountUpdated|Testlast|test01@gmail.com|ACTIVE|M     |10524     |456       |FFF   |1     |PRIMARY    |1         |1     |40100      |v1     |397      |OK           |400          |
    |USER-16   |1     |apit@gmail.com|letmein |TestingAPI01|Test@12  |TestAccountUpdated|Testlast|test01@gmail.com|ACTIVE|M     |10524     |456       |FFF   |1     |PRIMARY    |1         |1     |20000      |v1     |397      |OK           |400          |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Update User API
----------------
    * User Enter Update User API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/users/"<userid>
    * User enter User Details Update User API <userid> <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>
    * User enter List: addresses Update User API <addressid> <postalCode> <doorNumber> <street> <cityId> <addressType>
    * User enter locations: id Update User API <locationId>
    * User enter roles: Update User API <roleId>
    * User Call Update User API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database Update User API <userid>
    * Validate User Details Update User API <userid> <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>
    * Validate List: addresses Update User API <addressid> <postalCode> <doorNumber> <street> <cityId> <addressType>
    * Validate locations: id Update User API <locationId>
    * Validate roles: Update User API <roleId>

LogOut API
----------------
    *User logout from kraydel <logout_status>
