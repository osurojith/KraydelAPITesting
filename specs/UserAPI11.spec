User API view picture by ID
=====================
Created by OsuraL on 8/31/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

    |TestCaseId|userid|version|username|password|logout_status|response_code|status_code|status |
    |----------|------|-------|--------|--------|-------------|-------------|-----------|-------|
    |USER-29/30|232   |v1     |darshana|letmein |OK           |200          |20000      |Success|




LogIn API
----------------
    * User get refresh token <username>,<password> and <response_code>
    * User send refresh token <response_code>

User API
----------------
    * User enter User API view Picture by id "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/user/"<userid>"/picture"
    * User call the User API view Picture by id
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Get data from kraydel database <userid>
    * Validate view Picture by id API

LogOut API
----------------
    * User logout from kraydel <logout_status>
