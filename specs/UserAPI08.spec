Update Status API
=====================
Created by OsuraL on 9/4/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
 | id                      |version|status_code|request_code|status  |username       |password|logout_status|response_code|role_name|Userstatus|
 |dvgw9gEp70ovgOfAOOK69Q== |   v1  |    20000  |    200     | Success|darshana |letmein |OK           | 200         |null     |INACTIVE|



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
       *User send refresh token <response_code>

Update Status API
----------------


    * User enter Update Status API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/users/"<id>"/status"
    * Update API Body <Userstatus>
    * User call the Update Status API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Back End Update Status API <Userstatus> <id>




LogOut API
----------------
   *User logout from kraydel <logout_status>
