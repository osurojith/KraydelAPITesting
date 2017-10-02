View elders assigned to a given  carer
=====================


    |TestCaseId|version|username        |password|logout_status|response_code|status_code|request_code|status |
    |----------|-------|----------------|--------|-------------|-------------|-----------|------------|-------|
    |USER-19/20|v1     |shehan@gmail.com|test@12 |OK           |200          |20000      |200         |Success|



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

View elders assigned to a given  carer
----------------
    * User enter View Elders Assigned API "/api/"<version>"/user/elders"
    * User call the View Elders Assigned API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database View Elders Assigned API <username>
    * Validate Content View Elders Assigned API

LogOut API
----------------
    * User logout from kraydel <logout_status>
