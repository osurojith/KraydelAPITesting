import com.thoughtworks.gauge.Step;
import io.restassured.path.json.JsonPath;
import utils.BaseClass;
import utils.HttpMethods;

import java.util.HashMap;
import java.util.Map;

public class GraphDataAPISteps extends BaseClass {


    @Step("User Enter Graph-Data API </api/><version></graph-data>")
    public void Enter_API(String part1, String part2, String part3) {
        this.api = System.getenv("URI") + part1 + part2 + part3;

    }

    @Step("User enter Graph Details Graph-Data API <baseStationId> <fromTime> <resultCount> <startIndex> <tableName> <toTime>")
    public void Enter_Alert_details(String baseStationId, String fromTime, String resultCount, String startIndex, String tableName, String toTime) {
        body = "{\n" +
                "\"baseStationId\": \"22-22-22-22\",\n" +
                "\"fromTime\": 1495026000,\n" +
                "\"resultCount\": 100,\n" +
                "\"startIndex\": 0,\n" +
                "\"tableName\": \"COM_MITRAI_SENSOR_EVENT_DATA_STREAM\",\n" +
                "\"toTime\": 1502975353\n" +
                "}\n";
    }

    @Step("User Call Graph-Data API")
    public void Call_the_API() {
        Map<String, String> header = new HashMap();

        this.response = HttpMethods.postMethodBody(this.api, header, body);
        this.jsonPath = new JsonPath(this.response.getBody().asString());
    }


}
