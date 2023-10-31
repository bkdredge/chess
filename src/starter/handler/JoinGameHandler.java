package handler;

import com.google.gson.Gson;
import request.*;
import result.JoinGameResult;
import service.JoinGameService;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

/**
 * A handler for joining a game
 */
public class JoinGameHandler implements Route {
    @Override public Object handle(Request request, Response response) {
        // extract the auth token from the Spark request to create a new request object
        String authToken = request.headers("authorization");

        // deserialize the Spark request to create a new request object
        var gson = new Gson().fromJson(request.body(), Map.class);

        // let the color conform to the "playerColor" from the request body
        // if it is null, let the color string be null by default
        String color = null;
        if (gson.size() > 1) {color = (String) gson.get("playerColor");}

        // use the auth token, the team color, and the gameID to parametrize a new request
        JoinGameRequest classRequest = new JoinGameRequest(authToken, color, ((Double) gson.get("gameID")).intValue());
        JoinGameService classService = new JoinGameService();
        JoinGameResult classResult = classService.joinGame(classRequest);

        // set the response code according to the message set by the service
        if (classResult.getMessage()==null) {
            response.status(200); return "{}";
        } else if (classResult.getMessage()=="bad request") {
            response.status(400);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else if (classResult.getMessage()=="unauthorized") {
            response.status(401);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else if (classResult.getMessage()=="already taken") {
            response.status(403);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else {
            response.status(500);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        }
    }
}
