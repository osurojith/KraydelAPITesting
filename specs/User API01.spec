User API
=====================
Created by OsuraL on 8/31/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

        | userid                     |version|status_code |request_code|status  |username |password	|logout_status|response_code|role_name|
        |   8LAw7a7AEa-GBwZKH7l9Ng== |   v1    |    20000 |    200     | Success  |darshana |letmein	|OK           | 200         |null     |




LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>


User API
----------------

    * User enter User API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/users/"<userid>
    * User call the User API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate User Content
    * Validate User
    * Validate User Address Details
    * Validate Locations
    * Validate Roles

LogOut API
----------------
   *User logout from kraydel <logout_status>
