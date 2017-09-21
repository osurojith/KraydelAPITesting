Create User API
=====================
Created by OsuraL on 9/1/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

      |username|password|logout_status|response_code|role_name|usernameC   |passwordC|firstname|lastname|email           |status|gender|postalCode|doorNumber|street|cityId|addressType|locationId|roleId|status_code|version|
      |darshana|letmein |OK           |200          |null     |TestingAPI047|Test@12  |Testfirst|Testlast|test28@gmail.com|ACTIVE|M     |10524     |456       |AAA   |1     |PRIMARY    |1         |1     |20000      |v1     |



LogIn API
----------------
    *User get refresh token <username>,<password> and <response_code>
    *User send refresh token <response_code>



Create User API
----------------
* User Enter Create User API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/users"
 *User enter User Details Create User API <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>
 *User enter List: addresses Create User API <postalCode> <doorNumber> <street> <cityId> <addressType>
 *User enter locations: id Create User API <locationId>
 *User enter roles: Create User API <roleId>
 * User Call Create User API
 * Validate Status Code <status_code>
  * Validate HTTP Response <response_code>
 * User gets data from kraydel database Create User API <email>
 *User Validate User Details Create User API <usernameC> <passwordC> <firstname> <lastname> <email> <status> <gender>
  *User Validate List: addresses Create User API <postalCode> <doorNumber> <street> <cityId> <addressType>
  *User Validate locations: id Create User API <locationId>
  *User Validate roles: Create User API <roleId>
 LogOut API
 ----------------
  *User logout from kraydel <logout_status>
