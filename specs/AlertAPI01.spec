Alert Search API GET
=====================
Created by OsuraL on 9/6/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

     |username|password|logout_status|response_code|status_code|version|
     |--------|--------|-------------|-------------|-----------|-------|
     |darshana|letmein |OK           |200          |20000      |v1     |

LogIn API
  ----------------
      *User get refresh token <username>,<password> and <response_code>
      *User send refresh token <response_code>



Alert API GET
----------------
 * User Enter Alert Search API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/alerts/search"
 * User Call Alert Search API
 * Validate Status Code <status_code>
 * Validate HTTP Response <response_code>
 * Validate Alert Content

LogOut API
----------------
   *User logout from kraydel <logout_status>
