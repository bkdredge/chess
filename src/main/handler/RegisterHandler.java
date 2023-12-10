package handler;

import request.*;
import result.*;
import service.RegisterService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * A handler for registering a new user account.
 */
public class RegisterHandler implements Route {
    @Override public Object handle(Request request, Response response) {
        // deserialize the Spark request to create a new request object
        RegisterRequest classRequest = new RegisterRequest(new Gson().fromJson(request.body(), Map.class));

        // create a result object using a new service object and the request
        RegisterService classService = new RegisterService();
        RegisterResult classResult = classService.register(classRequest);

        // set the response code according to the message set by the service
        if (classResult.getMessage()==null) {
            response.status(200);
            var map = new HashMap<String, String>();
            map.put("authToken", classResult.getAuthToken());
            map.put("username", classResult.getUsername());
            return new Gson().toJson(map);
        } else if (classResult.getMessage()=="bad request") {
            response.status(400);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else if (classResult.getMessage().equals("already taken")) {
            response.status(403);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else {
            response.status(500);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        }
    }
}
