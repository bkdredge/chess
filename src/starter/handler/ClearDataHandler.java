package handler;
import com.google.gson.Gson;
import result.ClearDataResult;
import service.ClearDataService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;
/**
 * A handler for clearing data.
 */
public class ClearDataHandler implements Route {
    @Override public Object handle(Request request, Response response) {
        ClearDataService classService = new ClearDataService();
        ClearDataResult classResult = classService.clear();
        if (classResult.getMessage() != null) {
            response.status(500);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else {
            response.status(200); return "{}";
        }
    }
}
