Role API Get Elder Roles
=====================


    |TestCaseId|version|username|password|logout_status|response_code|status_code|
    |----------|-------|--------|--------|-------------|-------------|-----------|
    |ROLE-03/04|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Role API
----------------
    * User enter Role API Get Elder Roles "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/roles/elder-roles/partial"
    * User call the Role API Get Elder Roles
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Content Get Elder Roles

LogOut API
----------------
    * User logout from kraydel <logout_status>
