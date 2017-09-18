Unassign-carer API
=====================
Created by OsuraL on 9/6/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
     |elderid|username|password|logout_status|response_code|userID|userRoleID|status_code|version|
     |160    |darshana|letmein |OK           |200          |140   |1         |20000      |v1     |

LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>

Unassign-carer API
----------------
 * User Enter Unassign-carer API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/"<elderid>"/unassign-carer"
 * User enter Elder Details Unassign-carer API <userID> <userRoleID>
 * User Call Unassign-carer API
 * User gets data from kraydel database Unassign-carer API <userID> <elderid> <userRoleID>
 * Validate Status Code <status_code>
 * Validate back end Unassign-carer API <userID> <elderid> <userRoleID>

 LogOut API
 ----------------
    *User logout from kraydel <logout_status>
