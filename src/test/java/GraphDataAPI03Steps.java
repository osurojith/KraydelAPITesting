import com.aut.BaseClass;
import com.aut.HttpMethodsFactory;
import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;


import java.util.HashMap;
import java.util.Map;

public class GraphDataAPI03Steps extends BaseClass {
    @Step("User enter ambient-temperature Graph Data API </api/><version></graph-data/ambient-temperature>")
    public void enter_api(String arg0, String arg1, String arg2) {
        api = System.getenv("URI") + arg0 + arg1 + arg2;
    }

    @Step("User enter parameter values ambient-temperature Graph Data API <elderId><fromTime><toTime><startIndex><resultCount><tableName>")
    public void enter_parameter_data(String elderId, String fromTime, String toTime, String startIndex, String resultCount, String tableName) {
        api = api + "?elderId=" + elderId + "&fromTime=" + fromTime + "&toTime=" + toTime + "&startIndex=" + startIndex + "&resultCount=" + resultCount + "&tableName=" + tableName + "";
    }

    @Step("User call the ambient-temperature Graph Data API")
    public void call_api() {
        Map<String, String> header = new HashMap();
        this.response = HttpMethodsFactory.getMethod(this.api, header);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }

    @Step("Get data from kraydel database ambient-temperature Graph Data API <elderId>")
    public void get_db_data(String elderId) {

    }

    @Step("Validate ambient-temperature Graph Data API")
    public void validate_activity_graph_details() {

    }
}
