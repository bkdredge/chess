package handler;

import request.ListGamesRequest;
import result.ListGamesResult;
import service.ListGamesService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

/**
 * A handler for listing out games for a user
 */
public class ListGamesHandler implements Route {
    @Override public Object handle(Request request, Response response) {
        // extract the auth token from the Spark request to create a new request object
        ListGamesRequest classRequest = new ListGamesRequest(request.headers("authorization"));

        // create a result object using a new service object and the request
        ListGamesService classService = new ListGamesService();
        ListGamesResult classResult = classService.listGames(classRequest);

        // set the response code according to the message set by the service
        if (classResult.getMessage()==null) {
            response.status(200);
            return new Gson().toJson(Map.of("games", classResult.getGames()));
        } else if (classResult.getMessage()=="unauthorized") {
            response.status(401);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else {
            response.status(500);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        }
    }
}
