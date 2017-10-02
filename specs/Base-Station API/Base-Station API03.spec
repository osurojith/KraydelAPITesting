Update Base station status
=====================


    |TestCaseId       |id|basestationtatus|version|username|password|logout_status|response_code|status_code|
    |-----------------|--|----------------|-------|--------|--------|-------------|-------------|-----------|
    |BASESTATION-05/06|9 |ONLINE          |v1     |darshana|letmein |OK           |200          |20000      |
    |BASESTATION-05/06|9 |ONLINE          |v1     |darshana|letmein |OK           |400          |40000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Update Base Station API
----------------

    * User enter Update Base Station API "/api/"<version>"/base-stations/"<id>"/status"
    * Update Base Station API Body <basestationtatus>
    * User call the Update Base Station API
    * User gets data from kraydel database  Update Base Station Status API <id>
    * Validate back Base Station Status <basestationtatus>
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>

LogOut API
----------------
    * User logout from kraydel <logout_status>