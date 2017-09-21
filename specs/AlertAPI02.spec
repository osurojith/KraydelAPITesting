Alert Update Status API PUT
=====================
Created by OsuraL on 9/6/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

     |username|password|logout_status|response_code|status_code|version|status       |sentalertid|statusVal|
     |darshana|letmein |OK           |200          |20000      |v1     |    SEEN     |     64    |     1   |
     |darshana|letmein |OK           |200          |20000      |v1     |ACKNOWLEDGED |     64    |     2   |
     |darshana|letmein |OK           |200          |20000      |v1     | DECLINED    |     64    |     3   |

LogIn API
  ----------------
      *User get refresh token <username>,<password> and <response_code>
      *User send refresh token <response_code>



Alert API PUT
----------------
 * User Enter Alert Update Status API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/alerts/"<sentalertid>"/user-alerts/status"
  * Update request body Alert Update Status API <status>
 * User Call Alert Update Status API
 * Validate Status Code <status_code>
 * Validate HTTP Response <response_code>
 * Validate back end Assign-carer API <sentalertid> <statusVal>

LogOut API
----------------
   *User logout from kraydel <logout_status>
