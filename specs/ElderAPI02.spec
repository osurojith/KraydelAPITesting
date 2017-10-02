Elder Search API view carers
=====================


    |TestCaseId |elder-ID|version|username|password|logout_status|response_code|status_code|status |
    |-----------|--------|-------|--------|--------|-------------|-------------|-----------|-------|
    |ELDER-05/06|221     |v1     |darshana|letmein |OK           |200          |20000      |Success|



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Elder Search API view carers
----------------

    * User enter Elder Search API view carers "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/"<elder-ID>"/carers"
    * User call the Elder Search API view carers
    * Validate HTTP Response <response_code>
    * Validate Status Code <status_code>
    * User gets data from kraydel database Elder Search API view carers <elder-ID>
    * Validate Elder Search API view carers Content <elder-ID>

LogOut API
----------------
    * User logout from kraydel <logout_status>
