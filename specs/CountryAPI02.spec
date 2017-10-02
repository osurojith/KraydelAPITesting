City API
=====================


    |TestCaseId|version|username|password|logout_status|response_code|status_code|
    |----------|-------|--------|--------|-------------|-------------|-----------|
    |ROLE-01/02|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Country API
----------------
    * User enter Country API2 "/api/"<version>"/countries/"<country-id>"/cities/partial"
    * User call the Country API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate City Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
