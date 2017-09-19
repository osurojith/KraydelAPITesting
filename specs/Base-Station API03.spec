Update Base Station Status API
=====================
Created by OsuraL on 9/4/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
     |id|version|status_code|request_code|status |username|password|logout_status|response_code|role_name|basestationtatus|
     |9 |v1     |20000      |200         |Success|darshana|letmein |OK           |200          |null     |ONLINE         |



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>


Update Base Station API
----------------


    * User enter Update Base Station API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/base-stations/"<id>"/status"
    * Update Base Station API Body <basestationtatus>
    * User call the Update Base Station API
    * User gets data from kraydel database  Update Base Station Status API <id>
    * Validate back Base Station Status <basestationtatus>
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>



LogOut API
----------------
   *User logout from kraydel <logout_status>