Role API
=====================
Created by OsuraL on 8/30/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     	|version  |status_code    |request_code   |status     |username			|password	|logout_status|response_code|role_name|
        | v1      |   20000       |  200          | Success   |darshana	        |letmein	|OK           | 200         |null     |






LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>

Role API
----------------
    * User enter Role API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/roles/partial"
    * User call the Role API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Content
    * Validate Role Data Id
    * Validate Role Data Name

LogOut API
----------------
   *User logout from kraydel <logout_status>
