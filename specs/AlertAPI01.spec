Alert Search API GET
=====================


    |TestCaseId   |version|username|password|logout_status|response_code|status_code|
    |-------------|-------|--------|--------|-------------|-------------|-----------|
    |ALERT-01/02  |v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Alert API GET
----------------
    * User Enter Alert Search API "/api/"<version>"/alerts/search"
    * User Call Alert Search API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Alert Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
