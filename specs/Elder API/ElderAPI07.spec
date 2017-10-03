Unassign-carer API
=====================


    |TestCaseId |userID|elderid   |userRoleID|username      |password|logout_status|response_code|status_code|version|
    |-----------|------|----------|----------|--------------|--------|-------------|-------------|-----------|-------|
    |ELDER-22/23|190   |210       |1         |darshana      |letmein |OK           |200          |20000      |v1     |
    |ELDER-22/23|190   |210       |2         |darshana      |letmein |OK           |200          |20000      |v1     |
    |ELDER-24   |190   |210       |1         |apit@gmail.com|letmein |OK           |400          |40100      |v1     |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Unassign-carer API
----------------
    * User Enter Unassign-carer API "/api/"<version>"/elders/"<elderid>"/unassign-carer"
    * User enter Elder Details Unassign-carer API <userID> <userRoleID>
    * User create Unassign-carer method request body
    * User Call Unassign-carer API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database Unassign-carer API
    * Validate back end Unassign-carer API

LogOut API
----------------
    * User logout from kraydel <logout_status>
