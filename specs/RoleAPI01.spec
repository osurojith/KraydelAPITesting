Role API Get User Roles
=====================


    |TestCaseId|version|username|password|logout_status|response_code|status_code|
    |----------|-------|--------|--------|-------------|-------------|-----------|
    |ROLE-01/02|v1     |darshana|letmein |OK           |200          |20000      |




LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Role API
----------------
    * User enter Role API Get User Roles "/api/"<version>"/roles/partial"
    * User call the Role API Get User Roles
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Content Get User Roles

LogOut API
----------------
    * User logout from kraydel <logout_status>
