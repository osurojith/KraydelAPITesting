User Search API
=====================
Created by OsuraL on 8/31/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

     |TestCaseId|version|status_code|request_code|status |username      |password|logout_status|response_code|
     |----------|-------|-----------|------------|-------|--------------|--------|-------------|-------------|
     |USER-05   |v1     |20000      |200         |Success|apit@gmail.com|letmein |OK           |200          |
     |USER-06/07|v1     |20000      |200         |Success|darshana      |letmein |OK           |200          |




LogIn API
----------------
    *User get refresh token <username>,<password> and <response_code>
    *User send refresh token <response_code>





User Search API
----------------
    *User enter Search API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/users/search"
    *User call the Search API
    *Validate Status Code <status_code>
    *Validate HTTP Response <response_code>
    *Validate Search API Users
    *Validate Search API Pagination


LogOut API
----------------
    *User logout from kraydel <logout_status>
