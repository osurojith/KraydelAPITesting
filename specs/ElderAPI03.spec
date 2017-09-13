Search Elder API
=====================
Created by OsuraL on 9/4/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
|version|status_code |status  |username |password	|logout_status|response_code|role_name|
     |   v1    |    20000 |Success| darshana |letmein	|OK           | 200         |null     |



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>




Elder Search API
----------------
 * User enter Elder Search API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/search"
 * User call the Elder Search API
 * Validate Status Code <status_code>
 *Validate HTTP Response <response_code>
 * Validate Elder Search API Users
 * Validate Elder Search API Pagination

LogOut API
----------------
   *User logout from kraydel <logout_status>
