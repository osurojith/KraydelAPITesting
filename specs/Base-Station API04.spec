Update Base Station API
=====================
Created by OsuraL on 9/4/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
 |id|version|status_code|response_code|status |username|password|logout_status|role_name|elderID|
 |9 |v1     |20000      |200          |Success|darshana|letmein |OK           |null     |183    |



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>



Update Base Station API
----------------


    * User enter Update Base Station API by ID "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/base-stations/"<id>
    * Update Base Station API by ID Body <elderID>
    * User call the Update Base Station API by ID
    * User gets data from kraydel database Update Base Station API by ID <elderID> <id>
    * Validate Status Code <status_code>
    *Validate HTTP Response <response_code>



LogOut API
----------------
   *User logout from kraydel <logout_status>
