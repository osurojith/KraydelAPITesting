
import com.aut.BaseClass;
import com.aut.DatabaseFactory;
import com.aut.EncryptionServiceImpl;
import com.aut.HttpMethodsFactory;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AlertAPI02Steps extends BaseClass {
    @Step("User Enter Alert Update Status API </api/><version></alerts/><sentalertid></user-alerts/status>")
    public void call_api(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        this.api = System.getenv("URI") + arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3) + arg4;
        System.out.println("API: " + api);
    }

    @Step("Update request body Alert Update Status API <status>")
    public void set_body(String status) {
        body = "{\n" +
                " \"alertStatus\": \"" + status + "\"\n" +
                "}";
        System.out.println("Body: " + body);
    }

    @Step("User Call Alert Update Status API")
    public void call_api() {
        System.out.println(body);
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.putMethodBody(this.api, header, body);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }


    @Step("Validate back end Assign-carer API <sentalertid> <statusVal>")
    public void validate_backend(String sentalertid, String status) throws SQLException, ClassNotFoundException {
        String sql = "select * from main.user_alert_details where id=" + sentalertid + " and status=" + status + "";
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
        Assert.assertEquals("No record found  main.user_alert_details.", true, getResults().next());
        getResults().previous();
    }
}
