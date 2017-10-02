User API view unassigned carer
=====================


    |TestCaseId|elderid|username|password|logout_status|response_code|status_code|request_code|status |version|
    |----------|-------|--------|--------|-------------|-------------|-----------|------------|-------|-------|
    |USER-27/28|212    |darshana|letmein |OK           |200          |20000      |200         |Success|v1     |



LogIn API
----------------
   * User get refresh token <username>,<password> and <response_code>
   * User send refresh token <response_code>

User API
----------------
   * User enter User API view unassigned carer "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/users/elders/"<elderid>"/unassigned"
   * User call the User API view unassigned carer
   * Validate Status Code <status_code>
   * Validate HTTP Response <response_code>
   * Get data from database and Validate User API view unassigned carer <elderid>
   * Validate carer Content view unassigned carer

LogOut API
----------------
   * User logout from kraydel <logout_status>
