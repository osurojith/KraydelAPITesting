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
    public static  Response response;
    public static JsonPath jsonPath;
    public static ResultSet results;

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = System.getenv("URI") +api;
        System.out.println("API: " + getApi());
    }

    public JsonPath getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(JsonPath jsonPath) {
        this.jsonPath = jsonPath;
    }

    public ResultSet getResults() {
        return results;
    }

    public void setResults(ResultSet results) {
        this.results = results;
    }
}
