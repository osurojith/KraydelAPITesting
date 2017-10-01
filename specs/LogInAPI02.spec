API Test LogIn 
=====================
Created by OsuraL on 8/25/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

        |username			|password	|response_code			  |status_code|
     	|darshana1	        |letmein	| 400                     |44102      |
        |darshana	        |letmein1	| 400                     |44102      |

LogIn API (Get refresh token)
----------------
    * User enter API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8081/kraydel-oauth-server/oauth/token"
    * User enter credentials <username>,<password>
    * User call the get token API
    * Validate Status Code <status_code>
    * Validate invalid HTTP Response <response_code>


