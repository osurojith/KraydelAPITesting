Country API
=====================
Created by OsuraL on 8/30/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
  	|version|status_code|response_code|status |username|password|logout_status|
    |v1     |20000      |200          |Success|darshana|letmein |OK           |

  LogIn API
     ----------------
         *User get refresh token <username>,<password> and <response_code>
          *User send refresh token <response_code>




Country API
----------------
    * User enter Country API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/countries/partial"
    * User call the Country API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Validate Country Content



LogOut API
----------------
   *User logout from kraydel <logout_status>
