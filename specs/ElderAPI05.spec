Update Elder API
=====================
Created by OsuraL on 9/1/2017


|elderid                 |username|password|logout_status|response_code|role_name|firstName       |lastName|email          |gender|ethnicityId             |religionId              |dateOfBirth|countryId               |addressID                |postalCode|doorNumber|street|cityId                  |addressType|locationId              |status_code|version|status|phoneID                 |phoneNumber|phoneType|baseStationid           |tvBrandId               |elderstatus|
|A0dOVlgwZJJpRh7E2QPj6w==|darshana|letmein |OK           |200          |null     |TestFirstUpdated|TestLast|test6@gmail.com|M     |MKacoUo0nAB6neZVhIHutw==|MKacoUo0nAB6neZVhIHutw==|2017-12-07 |MKacoUo0nAB6neZVhIHutw==|PWSFC9y_IHGlEsoBf9U4uQ==|10524     |456       |AAA   |MKacoUo0nAB6neZVhIHutw==|PRIMARY    |MKacoUo0nAB6neZVhIHutw==|20000      |v1     |ACTIVE|sH6DRWoKl4yrby5zbd_q5A==|0112654659 |PRIMARY  |MKacoUo0nAB6neZVhIHutw==|MKacoUo0nAB6neZVhIHutw==|ACTIVE     |



LogIn API
   ----------------
       *User get refresh token <username>,<password> and <response_code>
        *User send refresh token <response_code>

Update Elder API
----------------
* User Enter Update Elder API "http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/elders/"<elderid>
 *User enter Elder Details Update Elder API <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
 *User enter List: addresses Update Elder API <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>
 *User enter phoneNumber: Update Elder API <phoneID> <phoneNumber> <phoneType>
 *User enter baseStation: Update Elder API <baseStationid> <tvBrandId>
 * User Call Update Elder API
 * Validate Status Code <status_code>
 *User enter Elder Details Update Elder API validating <elderid> <firstName> <lastName> <email> <gender> <ethnicityId> <religionId><dateOfBirth> <locationId> <elderstatus>
 *User enter List: addresses Update Elder API validating <countryId> <addressID> <postalCode> <doorNumber> <street> <cityId> <addressType>
 *User enter phoneNumber: Update Elder API validating <phoneID> <phoneNumber> <phoneType>
 *User enter baseStation: Update Elder API validating <baseStationid> <tvBrandId>



LogOut API
----------------
   *User logout from kraydel <logout_status>
