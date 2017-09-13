Location API
=====================
Created by OsuraL on 8/30/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     	|version|status_code |request_code|status  |username |password	|logout_status|response_code|role_name|
        |   v1    |    20000 |    200     | Success|darshana |letmein	|OK           | 200         |null     |



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>



Location API
----------------
    * User enter Location API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/locations/partial"
    * User call the Location API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Location Content
    * Validate Location Data Id
    * Validate Location Data Name

LogOut API
----------------
   *User logout from kraydel <logout_status>
