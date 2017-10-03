
import com.aut.*;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ElderAPI02Steps extends ElderAPIFactory {

    @Step("User enter Elder Search API view carers </api/><version></elders/><elder-ID></carers>")
    public void Enter_API(String arg0, String arg1, String arg2, long arg3, String arg4) throws Exception {
        setApi(arg0 + arg1 + arg2 + EncryptionServiceImpl.encryptToString(arg3) + arg4);
    }

    @Step("User call the Elder Search API view carers")
    public void Call_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        setJsonPath(new JsonPath(this.response.getBody().asString()));
    }

    @Step("User store API returned values Elder Search API view carers")
    public void store_API_Values() {
        get_Carer_Data_From_Carers_Array();
    }

    @Step("User gets data from kraydel database Elder Search API view carers <elder-ID>")
    public void get_data_from_database(String elderid) throws SQLException, ClassNotFoundException {
        getCarerDBDataAssignedToAElderById(elderid);
    }

    @Step("Validate Elder Search API view carers Content <elder-ID>")
    public void Validate_content(String elderId) throws SQLException, ClassNotFoundException {

            int tableRow = 0;
            Assert.assertEquals("No carer found", true,getCarerIdCount() >= 1);

            while (getResults().next()) {
                for (int i = 1; i <= getCarerIdCount(); i++) {
                    String val = Integer.toString(i - 1);

                    if (EncryptionServiceImpl.decryptToLong(getCarerId()).toString().equalsIgnoreCase(getResults().getString("id"))) {
                        tableRow++;
                        Assert.assertEquals("Validate person.id", getResults().getString("id"), EncryptionServiceImpl.decryptToLong(getCarerId()).toString());
                        Assert.assertEquals("Validate person.id", getResults().getString("fname"), getFname());
                        Assert.assertEquals("Validate person.id", getResults().getString("lname"), getLname());
                        Assert.assertEquals("Validate person.id", getResults().getString("email"), getEmail());
                        Assert.assertEquals("Validate person.id", getResults().getString("roleid"), getRoleId());
                        Assert.assertEquals("Validate person.id", getResults().getString("rolename"), getRoleName());
                        Assert.assertEquals("Validate person.id", getResults().getString("username"), getUsername());

                    }

                }
            }
            Assert.assertEquals("Data miss match API:DB", getCarerIdCount(), tableRow);
        }




}
