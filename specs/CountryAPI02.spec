City API
=====================
Created by OsuraL on 8/30/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
      |country-id|version|status_code|status |username|password|logout_status|response_code|role_name|
      |----------|-------|-----------|-------|--------|--------|-------------|-------------|---------|
      |1         |v1     |20000      |Success|darshana|letmein |OK           |200          |null     |

LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>






Country API
----------------
    * User enter Country API2 "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/countries/"<country-id>"/cities/partial"
    * User call the Country API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate City Content


LogOut API
----------------
   *User logout from kraydel <logout_status>
