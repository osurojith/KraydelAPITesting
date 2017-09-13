package utils;

import com.thoughtworks.gauge.Step;
import org.junit.Assert;

public class CommonUtils extends BaseClass{
    @Step("User Enter Alert API POST <api> <version> <id>")
    public void Enter_API(String api,String version,String id) {
        this.api=api.replace("<version>",version).replace("<id>",id);
    }

    @Step("Validate HTTP Response <response_code>")
    public void Validate_Http_Response(int code) {
        Assert.assertEquals(code, response.getStatusCode());

    }

    @Step("Validate Status Code <status_code>")
    public void Validate_status_code(String code) {
        status_code=jsonPath.getString("statusCode");
        Assert.assertEquals(code, jsonPath.getString("statusCode"));
    }

}
