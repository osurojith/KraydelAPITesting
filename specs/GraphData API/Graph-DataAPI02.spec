Absolute-light Graph Data API
=====================
Created by OsuraL on 9/26/2017

This is an executable specification file which follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

 |version|elderId|fromTime     |toTime       |startIndex|resultCount|tableName                             |status_code|response_code|
 |-------|-------|-------------|-------------|----------|-----------|--------------------------------------|-----------|-------------|
 |v1     |70     |1505908926991|1505964610991|0         |100        |COM_MITRAI_DEVICE_DATA_IN_EVENT_STREAM|20000      |202          |

     
Activity Graph Data API
----------------
    * User enter absolute-light Graph Data API "/api/"<version>"/graph-data/absolute-light"
    * User enter parameter values absolute-light Graph Data API <elderId><fromTime><toTime><startIndex><resultCount><tableName>
    * User call the absolute-light Graph Data API
    * Validate Status Code <status_code>
    * Validate HTTP Response <response_code>
    * Get data from kraydel database absolute-light Graph Data API <elderId>
    * Validate absolute-light Graph Data API