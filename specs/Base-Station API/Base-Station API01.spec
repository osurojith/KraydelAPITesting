Get Base-Station API
=====================


    |TestCaseId       |version|username|password|logout_status|response_code|status_code|
    |-----------------|-------|--------|--------|-------------|-------------|-----------|
    |BASESTATION-01/02|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Get Base-Station API
----------------
    * User enter Get Base-Station API "/api/"<version>"/base-stations/partial"
    * User call the Get Base-Station API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate content Get Base-Station API

LogOut API
----------------
    * User logout from kraydel <logout_status>
