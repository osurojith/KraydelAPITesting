Assigned to a elder Base Station API
=====================


    |TestCaseId       |id|elderID|basestationtatus|version|username|password|logout_status|response_code|status_code|
    |-----------------|--|-------|----------------|-------|--------|--------|-------------|-------------|-----------|
    |BASESTATION-08/09|9 |190    |ONLINE          |v1     |darshana|letmein |OK           |200          |20000      |
    |BASESTATION-10   |0 |190    |ONLINE          |v1     |darshana|letmein |OK           |400          |40000      |
    |BASESTATION-11   |9 |0      |ONLINE          |v1     |darshana|letmein |OK           |400          |40000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Update Base Station API
----------------

    * User enter Update Base Station API by ID "/api/"<version>"/base-stations/"<id>
    * Update Base Station API by ID Body <elderID>
    * User call the Update Base Station API by ID
    * User gets data from kraydel database Update Base Station API by ID <elderID> <id>
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>

LogOut API
----------------
    * User logout from kraydel <logout_status>
