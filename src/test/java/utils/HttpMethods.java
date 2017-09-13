package utils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class HttpMethods {


    public static Response postMethod(String api,Map<String,String> elements,Map<String,String> header) {
        RestAssured.baseURI = api;

        Response r = given().formParameters(elements).
                header(header.get("headername"), header.get("headervalue")).
                when().
                post("");

        String bodyContent = r.getBody().asString();
        System.out.println(bodyContent);
       return r;

    }
    public static Response getMethod(String api,Map<String,String> header) {
        Response r = given().header(header.get("headername"), header.get("headervalue")).when().get(api);
        String body = r.getBody().asString();
        System.out.println(body);
        return r;
    }
    public static Response postMethodBody(String api,Map<String,String> header, String body) {
        RestAssured.baseURI = api;
        Response r;
if(header.size()==0){
    System.out.println("xxxxxxxxxxxxxxxxxxx");
    r = given().
            contentType("application/json").
            body(body).
            when().
            post("");
}else {
    r = given().
            header(header.get("headername"), header.get("headervalue")).
            contentType("application/json").
            body(body).
            when().
            post("");
}
        String Contentbody = r.getBody().asString();
        System.out.println(Contentbody);
        return r;

    }

    public static Response putMethodBody(String api,Map<String,String> header, String body) {
        RestAssured.baseURI = api;

        Response r = given().
                header(header.get("headername"), header.get("headervalue")).
                contentType("application/json").
                body(body).
                when().
                put("");

        String Contentbody = r.getBody().asString();
        System.out.println(Contentbody);
        return r;

    }
}
