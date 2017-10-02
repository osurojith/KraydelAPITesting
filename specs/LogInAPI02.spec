API Test LogIn 
=====================

    |TestCaseId  |version|username|password|logout_status|response_code|status_code|
    |------------|-------|--------|--------|-------------|-------------|-----------|
    |LOGIN-02    |v1     |da56hana|letmein |OK           |400          |40000      |
    |LOGIN-03    |v1     |darshana|le58ein |OK           |400          |40000      |
    |LOGIN-04    |v1     |dar55ana|let66in |OK           |400          |40000      |



LogIn API (Get refresh token)
----------------
    * User enter API "/oauth/token"
    * User enter credentials <username>,<password>
    * User call the get token API
    * Validate Status Code <status_code>
    * Validate invalid HTTP Response <response_code>
