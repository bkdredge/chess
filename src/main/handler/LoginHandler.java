package handler;

import request.*;
import result.*;
import service.LoginService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * A handler for logging in.
 */
public class LoginHandler implements Route {
    @Override public Object handle(Request request, Response response) {
        // deserialize the Spark request to create a new request object
        LoginRequest classRequest = new LoginRequest(new Gson().fromJson(request.body(), Map.class));

        // create a result object using a new service object and the request
        LoginService classService = new LoginService();
        LoginResult classResult = classService.login(classRequest);

        // set the response code according to the message set by the service
        if (classResult.getMessage()==null) {
            response.status(200);
            var map = new HashMap<String, String>();
            map.put("authToken", classResult.getAuthToken());
            map.put("username", classResult.getUsername());
            return new Gson().toJson(map);
        } else if (classResult.getMessage()=="unauthorized") {
            response.status(401);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else {
            response.status(500);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        }
    }
}
