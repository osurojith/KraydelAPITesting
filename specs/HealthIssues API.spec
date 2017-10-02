HealthIssue API
=====================


    |TestCaseId  |version|username|password|logout_status|response_code|status_code|
    |------------|-------|--------|--------|-------------|-------------|-----------|
    |HEALTH-01/02|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

HealthIssue API
----------------
    * User enter HealthIssue API "/api/"<version>"/healthissues/partial"
    * User call the HealthIssue API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate HealthIssue Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
