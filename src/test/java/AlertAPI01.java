import KraydelEncryption.EncryptionServiceImpl;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import utils.BaseClass;
import utils.DBConn;
import utils.HttpMethods;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class AlertAPI01 extends BaseClass {
    @Step("User Enter Alert Search API </api/><version></alerts/search>")
    public void enter_api(String arg0, String arg1, String arg2) {
        this.api=System.getenv("URI")+arg0+arg1+arg2;
        System.out.println(api);
    }

    @Step("User Call Alert Search API")
    public void call_the_api() {
        System.out.println("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }
    public void get_db_data(String id) throws SQLException, ClassNotFoundException {
        String sql="select sent_alert_details.id as sentalertid,person.id as grampaid, sent_alert_details.status as status,sent_alert_details.alert_message_body as alertbody ,sent_alert_details.alert_message_subject as alertsubject,person.first_name fname,person.last_name lname  from main.user_alert_details join main.sent_alert_details on sent_alert_details.id=user_alert_details.sent_alert_details_id join main.person on person.id=sent_alert_details.grampa_id and user_alert_details.id="+EncryptionServiceImpl.decryptToLong(id)+"";
        System.out.println(sql);
        results = DBConn.getDBData(sql);
        Assert.assertEquals("No record found. ID:"+EncryptionServiceImpl.decryptToLong(id), true, results.next());
        results.previous();
    }
    @Step("Validate Alert Content")
    public void validate_alert_content() throws SQLException, ClassNotFoundException {
        for (int i = 1; i <= jsonPath.getList("content.alerts").size(); i++) {
            int count=0;
            String val = Integer.toString(i - 1);
            String userid=jsonPath.getString("content.alerts[" + val + "].id");
            String sentalertid=jsonPath.getString("content.alerts[" + val + "].sentAlertDetail.id");
            String status=jsonPath.getString("content.alerts[" + val + "].sentAlertDetail.status");
            String alertbody=jsonPath.getString("content.alerts[" + val + "].sentAlertDetail.alertMessageBody");
            String alertsubject=jsonPath.getString("content.alerts[" + val + "].sentAlertDetail.alertMessageSubject");
            String grampaid=jsonPath.getString("content.alerts[" + val + "].sentAlertDetail.grampa.id");
            String grampaname=jsonPath.getString("content.alerts[" + val + "].sentAlertDetail.grampa.elderName");


            get_db_data(userid);
            while (results.next()) {
                count++;
                Assert.assertEquals(results.getString("sentalertid"), EncryptionServiceImpl.decryptToLong(sentalertid).toString());
                Assert.assertEquals(results.getString("status"), status);
                Assert.assertEquals(results.getString("alertbody"), alertbody);
                Assert.assertEquals(results.getString("alertsubject"), alertsubject);
                Assert.assertEquals(results.getString("grampaid"), EncryptionServiceImpl.decryptToLong(grampaid).toString());
                Assert.assertEquals(results.getString("fname")+" "+results.getString("lname"), grampaname);

            }

            Assert.assertEquals("Data miss match API:DB",1,count);




        }
    }
}
