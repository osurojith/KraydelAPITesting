Update Status API
=====================

   
    |TestCaseId|id |version|username      |password|logout_status|response_code|Userstatus|status_code|status |
    |----------|---|-------|--------------|--------|-------------|-------------|----------|-----------|-------|
    |USER-21/25|210|v1     |darshana      |letmein |OK           |200          |INACTIVE  |20000      |Success|
    |USER-22/25|210|v1     |darshana      |letmein |OK           |200          |ACTIVE    |20000      |Success|
    |USER-23/25|210|v1     |darshana      |letmein |OK           |200          |DELETED   |20000      |Success|
    |USER-24/25|210|v1     |darshana      |letmein |OK           |200          |LOCKED    |20000      |Success|
    |USER-26   |210|v1     |apit@gmail.com|letmein |OK           |200          |INACTIVE  |40100      |Success|



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Update Status API
----------------
    * User enter Update Status API "/api/"<version>"/users/"<id>"/status"
    * Update API Body <Userstatus>
    * User call the Update Status API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database Update Status API <id>
    * Validate Back End Update Status API <Userstatus> <id>

LogOut API
----------------
    * User logout from kraydel <logout_status>
