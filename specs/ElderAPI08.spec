View Elders who isnt Assigned to a device API
=====================
Created by OsuraL on 9/6/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
     
    |TestCaseId |username      |password|logout_status|response_code|status_code|version|
    |-----------|--------------|--------|-------------|-------------|-----------|-------|
    |ELDER-25/27|darshana      |letmein |OK           |200          |20000      |v1     |
    |ELDER-26/27|apit@gmail.com|letmein |OK           |200          |20000      |v1     |

LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

View Assigned API
----------------
    * User Enter View Assigned API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/unassigned/partial"
    * User Call View Assigned API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * User gets data from kraydel database View Assigned API
    * Validate Content View Assigned API

LogOut API
----------------
    * User logout from kraydel <logout_status>
