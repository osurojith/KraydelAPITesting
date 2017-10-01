import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class GraphDataAPI01Steps extends BaseClass {
    @Step("User enter Activity Graph Data API <http://ec2-54-76-61-210.eu-west-1.compute.amazonaws.com:8080/kraydel-server/api/><version></graph-data/activity>")
    public void enter_api(String arg0,String arg1,String arg2) {
        api=arg0+arg1+arg2;
    }

    @Step("User enter parameter values Activity Graph Data API <elderId><fromTime><toTime><startIndex><resultCount><tableName>")
    public void enter_parameter_data(String elderId, String fromTime, String toTime, String startIndex, String resultCount, String tableName) {
        api=api+"?elderId="+elderId+"&fromTime="+fromTime+"&toTime="+toTime+"&startIndex="+startIndex+"&resultCount="+resultCount+"&tableName="+tableName+"";
    }

    @Step("User call the Activity Graph Data API")
    public void call_api() {
        Map<String, String> header = new HashMap();
        this.response = HttpMethods.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("Get data from kraydel database Activity Graph Data API <elderId>")
    public void get_db_data(String elderId) {

    }

    @Step("Validate Activity Graph Data API")
    public void validate_activity_graph_details() {

    }


}
