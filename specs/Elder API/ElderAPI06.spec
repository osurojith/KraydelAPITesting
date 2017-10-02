Assign-carer API
=====================


    |TestCaseId |userID|elderid   |userRoleID|username      |password|logout_status|response_code|status_code|version|
    |-----------|------|----------|----------|--------------|--------|-------------|-------------|-----------|-------|
    |ELDER-18/20|190   |210       |1         |darshana      |letmein |OK           |200          |20000      |v1     |
    |ELDER-19/20|190   |210       |2         |darshana      |letmein |OK           |200          |20000      |v1     |
    |ELDER-21   |190   |210       |1         |apit@gmail.com|letmein |OK           |400          |40100      |v1     |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Assign-carer API
----------------
    * User Enter Assign-carer API "/api/"<version>"/elders/"<elderid>"/assign-carer"
    * User enter Elder Details Assign-carer API <userID> <userRoleID>
    * User Call Assign-carer API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database Assign-carer API <userID> <elderid> <userRoleID>
    * Validate back end Assign-carer API <userID> <elderid> <userRoleID>

LogOut API
----------------
    * User logout from kraydel <logout_status>
