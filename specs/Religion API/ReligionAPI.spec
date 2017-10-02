Religions API
=====================


    |TestCaseId    |version|username|password|logout_status|response_code|status_code|
    |--------------|-------|--------|--------|-------------|-------------|-----------|
    |RELIGION-01/02|v1     |darshana|letmein |OK           |200          |20000      |



LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

Religions API
----------------
    * User enter Religions API "/api/"<version>"/religions/partial"
    * User call the Religions API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Religions Content

LogOut API
----------------
    * User logout from kraydel <logout_status>
