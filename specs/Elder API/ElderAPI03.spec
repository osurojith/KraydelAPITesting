Search Elder API
=====================


    |TestCaseId |version|username      |password|logout_status|response_code|status_code|status |
    |-----------|-------|--------------|--------|-------------|-------------|-----------|-------|
    |ELDER-07/09|v1     |darshana      |letmein |OK           |200          |20000      |Success|
    |ELDER-08/09|v1     |apit@gmail.com|letmein |OK           |200          |20000      |Success|



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Elder Search API
----------------
    * User enter Elder Search API "/api/"<version>"/elders/search"
    * User call the Elder Search API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Get data from database and Validate Elder Search API Users
    * Validate Elder Search API Pagination

LogOut API
----------------
    * User logout from kraydel <logout_status>
