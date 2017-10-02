package CommonSteps;


import com.aut.BaseClass;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;

public class CommonUtils extends BaseClass {

    @Step("Validate HTTP Response <response_code>")
    public void Validate_Http_Response(int code) {
        Assert.assertEquals(code, response.getStatusCode());

    }

    @Step("Validate Status Code <status_code>")
    public void Validate_status_code(String code) {
        status_code= getJsonPath().getString("statusCode");
        System.out.println(status_code);
        Assert.assertEquals(code, getJsonPath().getString("statusCode"));
    }

}
