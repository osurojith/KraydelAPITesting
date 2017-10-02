Location API
=====================


    |TestCaseId    |version|username|password|logout_status|response_code|status_code|
    |--------------|-------|--------|--------|-------------|-------------|-----------|
    |LOCATION-01/02|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Location API
----------------
    * User enter Location API "/api/"<version>"/locations/partial"
    * User call the Location API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Location Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
