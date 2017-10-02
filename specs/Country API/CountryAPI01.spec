Country API
=====================


    |TestCaseId   |version|username|password|logout_status|response_code|status_code|
    |-------------|-------|--------|--------|-------------|-------------|-----------|
    |COUNTRY-01/02|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Country API
----------------
    * User enter Country API "/api/"<version>"/countries/partial"
    * User call the Country API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Country Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
