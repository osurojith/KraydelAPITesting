View elders assigned to a given  carer
=====================
Created by OsuraL on 9/4/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

       |version|status_code |request_code|status  |username |password	|logout_status|response_code|role_name|
       |   v1    |    20000 |    200     | Success  |sampathd@mitrai.com |test@12|OK           | 200         |null     |



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>


View elders assigned to a given  carer
----------------


    * User enter View Elders Assigned API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/user/elders"
    * User call the View Elders Assigned API
    *User gets data from kraydel database View Elders Assigned API <username>
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Content View Elders Assigned API


LogOut API
----------------
   *User logout from kraydel <logout_status>
