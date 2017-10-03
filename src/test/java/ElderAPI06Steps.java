
import com.aut.*;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ElderAPI06Steps extends ElderAPIFactory {


    @Step("User Enter Assign-carer API </api/><version></elders/><elderid></assign-carer>")
    public void Call_API(String arg0, String arg1, String arg2, String elderId, String arg4) throws Exception {
        setElderId(elderId);
        setApi(arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(Long.parseLong(elderId)) + arg4);
    }

    @Step("User enter Elder Details Assign-carer API <userID> <userRoleID>")
    public void Enter_details(String carerID, String carerRoleID) throws Exception {
        setCarerId(carerID);
        setCarerRoleId(carerRoleID);

    }
    @Step("User create Assign-carer method request body")
    public void create_Request_Body() throws Exception {
        body = "{\n" +
                " \"user\": {\"id\": \"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getCarerId())) + "\"},\n" +
                "    \"grampaRole\": {\"id\": \"" + EncryptionServiceImpl.encryptToString(Long.parseLong(getCarerRoleId())) + "\"}\n" +
                "}";
        System.out.println("Body: " + body);
    }

    @Step("User Call Assign-carer API")
    public void Call_API() {

        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.putMethodBody(this.api, header, body);
        setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User gets data from kraydel database Assign-carer API")
    public void get_db_data() throws SQLException, ClassNotFoundException, java.lang.NullPointerException {
        getElder_CarerAssignedStatusDB();
    }

    @Step("Validate back end status Assign-carer API")
    public void Validate_backend() throws SQLException, ClassNotFoundException {
            while (getResults().next()) {
                Assert.assertEquals("Validate elder_user", getResults().getString("status"), "1");
            }

    }


}
