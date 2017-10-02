Ethnicity API
=====================


    |TestCaseId     |version|username|password|logout_status|response_code|status_code|
    |---------------|-------|--------|--------|-------------|-------------|-----------|
    |ETHNICITY-01/02|v1     |darshana|letmein |OK           |200          |20000      |

LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Ethnicity API
----------------
    * User enter Ethnicity API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/ethnicities/partial"
    * User call the Ethnicity API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Ethnicity Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
