User API
=====================


    |TestCaseId|userid|version|status_code|request_code|status |username      |password|logout_status|response_code|
    |----------|------|-------|-----------|------------|-------|--------------|--------|-------------|-------------|
    |USER-01/03|185   |v1     |20000      |200         |Success|darshana      |letmein |OK           |200          |
    |USER-02   |185   |v1     |20000      |200         |Success|apit@gmail.com|letmein |OK           |200          |
    |USER-04   |011   |v1     |20000      |200         |Success|darshana      |letmein |OK           |200          |



 LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

 User API
----------------
    * User enter User API "/api/"<version>"/users/"<userid>
    * User call the User API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Get data from database and Validate User API <userid>
    * Validate User Content
    * Validate User
    * Validate User Address Details
    * Validate Locations
    * Validate Roles

 LogOut API
----------------
    * User logout from kraydel <logout_status>
