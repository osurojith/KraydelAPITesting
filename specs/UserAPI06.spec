User Get Picture API
=====================


    |TestCaseId|version|status |username|password|logout_status|response_code|status_code|
    |----------|-------|-------|--------|--------|-------------|-------------|-----------|
    |USER-17/18|v1     |Success|darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

User get Picture API
----------------
    * User enter Get Picture API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/users/picture"
    * User call the Get Picture API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database get Picture API <username>
    * Validate Get Picture API

LogOut API
----------------
    * User logout from kraydel <logout_status>
