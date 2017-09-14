View Assigned API
=====================
Created by OsuraL on 9/6/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
|elderid                    |username	|password|logout_status|response_code|userID                   |userRoleID                |status_code|version|
| FRNFFf8mHNU5Lwva5RaJ-A==  |darshana	|letmein |OK           | 200         |TEXB76f0aS2d4aJ1FpzmCw== |  MKacoUo0nAB6neZVhIHutw==|   20000   |v1|

LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>


View Assigned API
----------------
 * User Enter View Assigned API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/unassigned/partial"
 * User Call View Assigned API
 * Validate Status Code <status_code>
 * Validate Content View Assigned API

LogOut API
----------------
   *User logout from kraydel <logout_status>
