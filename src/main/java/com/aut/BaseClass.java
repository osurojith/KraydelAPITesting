package com.aut;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.ResultSet;

public class BaseClass {
    public String sql=null;
    public String api = null;
    public String body = null;
    public String username = null;
    public String password = null;
    public static String status_code=null;
    public static String token = null;
    public  Response response;
    public  JsonPath jsonPath;
    public  ResultSet results;

}
