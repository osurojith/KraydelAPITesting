import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class LogInAPISteps extends BaseClass {


    @Step("User enter API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8081/kraydel-oauth-server/oauth/token>")
    public void User_enter_API(String api) {
        this.api = api;
    }

    @Step("User enter credentials <username>,<password>")
    public void user_enter_credentials(String user, String pass) {

        this.username = user;
        this.password = pass;

    }

    @Step("User call the get token API")
    public void User_call_the_get_token_API() {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("username", username);
        parameters.put("password", password);
        parameters.put("grant_type", "password");

        Map<String, String> header = new HashMap<String, String>();
        header.put("headername", "Authorization");
        header.put("headervalue", "Basic YmFyQ2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=");


        response = HttpMethods.postMethod(api, parameters, header);
        jsonPath = new JsonPath(response.getBody().asString());

    }


    @Step("Get refresh token")
    public void Get_refresh_token() {
        token = jsonPath.getString("refresh_token");
        System.out.println("xxxxx   "+token);
    }


    @Step("User call the LogIn API")
    public void User_call_the_LogIn_API() {
        Map<String, String> elements = new HashMap<String, String>();
        elements.put("refresh_token", token);
        elements.put("grant_type", "refresh_token");

        Map<String, String> header = new HashMap<String, String>();
        header.put("headername", "Authorization");
        header.put("headervalue", "Basic YmFyQ2xpZW50SWRQYXNzd29yZDpzZWNyZXQ=");

        response = HttpMethods.postMethod(api, elements, header);
        jsonPath = new JsonPath(response.getBody().asString());

    }


    @Step("User get access token")
    public void User_get_access_token() {
        token = jsonPath.getString("access_token");
        System.out.println(token);
    }


    @Step("User enter logout API <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8081/kraydel-oauth-server/logout>")
    public void user_enter_logout_API(String api) {
        this.api = api;
    }

    @Step("User call logout API")
    public void User_call_logout_API() {
        Map<String, String> header = new HashMap<String, String>();
        header.put("headername", "Authorization");
        header.put("headervalue", "Bearer " + token);

        Map<String, String> elements = new HashMap<String, String>();


        response = HttpMethods.postMethod(api, elements, header);
        jsonPath = new JsonPath(response.getBody().asString());
    }

    @Step("User logout using Acess Token and Validateg Acess Token and Validate <logout_status>")
    public void Log_out(String status) {
        Assert.assertEquals(status, jsonPath.getString("status"));

    }


    @Step("Validate invalid HTTP Response <response_code>")
    public void Validate_invalid_HTTP_Response(int code) {
        Assert.assertEquals(code, response.getStatusCode());
    }
}
