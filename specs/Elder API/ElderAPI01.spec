Elder Search API By ID
=====================


    |TestCaseId |elder-ID|version|username      |password|logout_status|response_code|status_code|status |
    |-----------|--------|-------|--------------|--------|-------------|-------------|-----------|-------|
    |ELDER-01/03|13      |v1     |darshana      |letmein |OK           |200          |20000      |Success|
    |ELDER-02/03|13      |v1     |apit@gmail.com|letmein |OK           |200          |20000      |Success|
    |ELDER-04   |13      |v1     |darshana      |letmein |OK           |400          |40000      |Success|



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Elder Search API By ID
----------------
    * User enter Elder Search API By ID "/api/"<version>"/elders/"<elder-ID>
    * User call the Elder Search API By ID
    * Validate HTTP Response <response_code>
    * Validate Status Code <status_code>
    * User gets data from kraydel database Search API By ID <elder-ID>
    * Validate Elder Search API By ID Users
    * Validate Elder Search API By ID Address
    * Validate Health Issues

LogOut API
----------------
    * User logout from kraydel <logout_status>
