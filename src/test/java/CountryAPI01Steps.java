import KraydelEncryption.EncryptionServiceImpl;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.BaseClass;
import utils.DBConn;
import utils.HttpMethods;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CountryAPI01Steps extends BaseClass {


    @Step("User enter Country API </api/><version></countries/partial>")
    public void User_enter_Country_API(String part1, String version, String part2) {
        this.api = System.getenv("URI")+part1 + version + part2;
        System.out.println("API: "+api);
    }

    @Step("User call the Country API")
    public void User_call_the_Country_API() {
        Map<String, String> header = new HashMap();
        header.put("headername", "Authorization");
        header.put("headervalue", "bearer " + LogInAPISteps.token);
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    public void get_db_data(String id,String tableName) throws SQLException, ClassNotFoundException {
        String sql=null;
        if(tableName.equalsIgnoreCase("country")) {
            sql = "select * from main.country where id=" + EncryptionServiceImpl.decryptToLong(id) + "";
            results = DBConn.getDBData(sql);
            System.out.println(sql);
            Assert.assertEquals("No record found  main.Country ID:" + id, true, results.next());
            results.previous();
        }
         if(tableName.equalsIgnoreCase("city")){
             sql = "select * from main.city where country_id=" + EncryptionServiceImpl.decryptToLong(id) + "";
             results = DBConn.getDBData(sql);
             System.out.println(sql);
             Assert.assertEquals("No record found  main.City ID:" + id, true, results.next());
             results.previous();
         }

    }

    @Step("Validate Country Content")
    public void Validate_Country_Content() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.countries").size(); i++) {
                int count=0;
                String val = Integer.toString(i - 1);

               String id=jsonPath.getString("content.countries[" + val + "].id");
               String name=jsonPath.getString("content.countries[" + val + "].name");

                get_db_data(id,"country");
                while(results.next()) {
                    count++;
                    Assert.assertEquals("Validate main.Country.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    Assert.assertEquals("Validate main.Country.name", results.getString("name"), name);
                }
                Assert.assertEquals("Data miss match",1,count);
            }

        }
    }



    @Step("Validate City Content")
    public void Validate_City_Content() throws SQLException, ClassNotFoundException {
        if (status_code.equals("20000")) {
            for (int i = 1; i <= jsonPath.getList("content.cities").size(); i++) {
                int count=0;
                String val = Integer.toString(i - 1);

                String id=jsonPath.getString("content.cities[" + val + "].id");
                String name=jsonPath.getString("content.cities[" + val + "].name");

                get_db_data(id,"city");
                while(results.next()) {
                    count++;
                    Assert.assertEquals("Validate main.City.id", results.getString("id"), EncryptionServiceImpl.decryptToLong(id).toString());
                    Assert.assertEquals("Validate main.City.name", results.getString("name"), name);
                }
                Assert.assertEquals("Data miss match",1,count);
            }
          }
    }


    @Step("User enter Country API2 <http://ec2-52-212-72-231.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></countries/><country-id></cities/partial>")
    public void User_enter_Country_API2(String part1, String part2, String part3, long part4, String part5) throws Exception {
        this.api = part1 + part2 + part3 + EncryptionServiceImpl.encryptToString(part4) + part5;
    }
}
