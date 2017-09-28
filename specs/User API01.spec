User API
=====================
Created by OsuraL on 8/31/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

        | userid                     |version|status_code |request_code|status  |username |password	|logout_status|response_code|role_name|
        |   185                      |   v1    |    20000 |    200     | Success  |darshana |letmein	|OK           | 200         |null     |
        |   185                      |   v1    |    20000 |    200     | Success  |apit@gmail.com |letmein	|OK           | 200         |null     |
        |   20                      |   v1    |    40000 |    400     | Success  |apit@gmail.com |letmein	|OK           | 200         |null     |




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
    * Get data from database and Validate User API <userid>
    * Validate User Content
    * Validate User
    * Validate User Address Details
    * Validate Locations
    * Validate Roles

LogOut API
----------------
   *User logout from kraydel <logout_status>
