Elder Search API By ID
=====================
Created by OsuraL on 9/4/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
    |elder-ID                |version|status_code|request_code|status |username|password|logout_status|response_code|role_name|
    |A0dOVlgwZJJpRh7E2QPj6w==|v1     |20000      |200         |Success|darshana|letmein |OK           |200          |null     |



LogIn API
  ----------------
      *User get refresh token <username>,<password> and <response_code>
       *User send refresh token <response_code>



Elder Search API By ID
----------------

    * User enter Elder Search API By ID "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/"<elder-ID>
    * User call the Elder Search API By ID
    * Validate HTTP Response <response_code>
    * Validate Status Code <status_code>
    * Validate Elder Search API By ID Users
    * Validate Elder Search API By ID Address
    * Validate Health Issues


LogOut API
----------------
   *User logout from kraydel <logout_status>
