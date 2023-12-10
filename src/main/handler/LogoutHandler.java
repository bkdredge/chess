package handler;

import com.google.gson.Gson;
import request.LogoutRequest;
import result.LogoutResult;
import service.LogoutService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

/**
 * A handler for logging out.
 */
public class LogoutHandler implements Route {
    @Override public Object handle(Request request, Response response) {
        // extract the auth token from the Spark request to create a new request object
        LogoutRequest classRequest = new LogoutRequest(request.headers("authorization"));

        // create a result object using a new service object and the request
        LogoutService classService = new LogoutService();
        LogoutResult classResult = classService.logout(classRequest);

        // set the response code according to the message set by the service
        if (classResult.getMessage()==null) {
            response.status(200); return "{}";
        } else if (classResult.getMessage()=="unauthorized") {
            response.status(401);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else {
            response.status(500);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        }
    }
}
