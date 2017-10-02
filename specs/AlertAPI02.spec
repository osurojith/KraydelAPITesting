Alert Update Status API PUT
=====================


    |TestCaseId |status      |sentalertid|statusVal|version|username|password|logout_status|response_code|status_code|
    |-----------|------------|-----------|---------|-------|--------|--------|-------------|-------------|-----------|
    |ALERT-03/04|SEEN        |64         |1        |v1     |darshana|letmein |OK           |200          |20000      |
    |ALERT-03/04|ACKNOWLEDGED|64         |1        |v1     |darshana|letmein |OK           |200          |20000      |
    |ALERT-03/04|DECLINED    |64         |1        |v1     |darshana|letmein |OK           |200          |20000      |


LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Alert API PUT
----------------
    * User Enter Alert Update Status API "/api/"<version>"/alerts/"<sentalertid>"/user-alerts/status"
    * Update request body Alert Update Status API <status>
    * User Call Alert Update Status API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate back end Assign-carer API <sentalertid> <statusVal>

LogOut API
----------------
    * User logout from kraydel <logout_status>
