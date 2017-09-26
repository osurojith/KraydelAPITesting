Activity Graph Data API
=====================
Created by OsuraL on 9/26/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

 |version|elderId|fromTime     |toTime       |startIndex|resultCount|tableName                             |status_code|response_code|
 |-------|-------|-------------|-------------|----------|-----------|--------------------------------------|-----------|-------------|
 |v1     |70     |1505908926991|1505964610991|0         |100        |COM_MITRAI_DEVICE_DATA_IN_EVENT_STREAM|20000      |202          |

     
Activity Graph Data API
----------------
    * User enter Activity Graph Data API "http://ec2-54-76-61-210.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/"<version>"/graph-data/activity"
    * User enter parameter values Activity Graph Data API <elderId><fromTime><toTime><startIndex><resultCount><tableName>
    * User call the Activity Graph Data API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Get data from kraydel database Activity Graph Data API <elderId>
    * Validate Activity Graph Data API