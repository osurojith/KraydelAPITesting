
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

public class AlertAPI01 extends BaseClass {
    @Step("User Enter Alert Search API </api/><version></alerts/search>")
    public void enter_api(String arg0, String arg1, String arg2) {
        this.api = System.getenv("URI") + arg0 + arg1 + arg2;
        System.out.println("API: " + api);
    }

    @Step("User Call Alert Search API")
    public void call_the_api() {

        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql = "select sent_alert_details.id as sentalertid,person.id as grampaid, sent_alert_details.status as status,sent_alert_details.alert_message_body as alertbody ,sent_alert_details.alert_message_subject as alertsubject,person.first_name fname,person.last_name lname  from main.user_alert_details join main.sent_alert_details on sent_alert_details.id=user_alert_details.sent_alert_details_id join main.person on person.id=sent_alert_details.grampa_id and user_alert_details.id=" + EncryptionServiceImpl.decryptToLong(id) + "";
        System.out.println(sql);
        setResults(DatabaseFactory.getDBData(sql));
        Assert.assertEquals("No record found. ID:" + EncryptionServiceImpl.decryptToLong(id), true, getResults().next());
        getResults().previous();
    }

    @Step("Validate Alert Content")
    public void validate_alert_content() throws SQLException, ClassNotFoundException {
        for (int i = 1; i <= getJsonPath().getList("content.alerts").size(); i++) {
            int count = 0;
            String val = Integer.toString(i - 1);
            String userid = getJsonPath().getString("content.alerts[" + val + "].id");
            String sentalertid = getJsonPath().getString("content.alerts[" + val + "].sentAlertDetail.id");
            String status = getJsonPath().getString("content.alerts[" + val + "].sentAlertDetail.status");
            String alertbody = getJsonPath().getString("content.alerts[" + val + "].sentAlertDetail.alertMessageBody");
            String alertsubject = getJsonPath().getString("content.alerts[" + val + "].sentAlertDetail.alertMessageSubject");
            String grampaid = getJsonPath().getString("content.alerts[" + val + "].sentAlertDetail.grampa.id");
            String grampaname = getJsonPath().getString("content.alerts[" + val + "].sentAlertDetail.grampa.elderName");


            get_db_data(userid);
            while (getResults().next()) {
                count++;
                Assert.assertEquals(getResults().getString("sentalertid"), EncryptionServiceImpl.decryptToLong(sentalertid).toString());
                Assert.assertEquals(getResults().getString("status"), status);
                Assert.assertEquals(getResults().getString("alertbody"), alertbody);
                Assert.assertEquals(getResults().getString("alertsubject"), alertsubject);
                Assert.assertEquals(getResults().getString("grampaid"), EncryptionServiceImpl.decryptToLong(grampaid).toString());
                Assert.assertEquals(getResults().getString("fname") + " " + getResults().getString("lname"), grampaname);

            }

            Assert.assertEquals("Data miss match API:DB", 1, count);


        }
    }
}
