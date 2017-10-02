TVBrand API
=====================


    |TestCaseId   |version|username|password|logout_status|response_code|status_code|
    |-------------|-------|--------|--------|-------------|-------------|-----------|
    |TVBRAND-01/02|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

TVBrand API
----------------
    * User enter TVBrand API "/api/"<version>"/tvbrands/partial"
    * User call the TVBrand API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate TVBrand Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
