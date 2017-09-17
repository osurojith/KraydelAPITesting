Alert API POST
=====================
Created by OsuraL on 9/6/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.
|username	|password|logout_status|response_code|role_name|alertMessage|alertSubject|subEventType|grampaId|status_code|version|
 |apit@gmail.com|test@12 |OK           | 200         |null     |test1 is good|Heyyy      |temp_high   |FRNFFf8mHNU5Lwva5RaJ-A==       |20000      |v1|


LogIn API
  ----------------
      *User get refresh token <username>,<password> and <response_code>
      *User send refresh token <response_code>



Alert API POST
----------------
 * User Enter Alert API POST "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/alerts"
 * User enter Alert Details Alert API POST <alertMessage> <alertSubject> <subEventType> <grampaId>
 * User Call Alert API POST
 * Validate Status Code <status_code>

