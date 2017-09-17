package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.sql.ResultSet;

public class BaseClass {
    public String api = null;
    public String body = null;
    public String username = null;
    public String password = null;
    public static String status_code=null;
    public static String token = null;
    public static Response response;
    public static JsonPath jsonPath;
    public static ResultSet results;
    public static ResultSet [] resultsArray=new ResultSet[3];
}
