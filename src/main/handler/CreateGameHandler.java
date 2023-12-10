package handler;

import request.*;
import result.*;
import service.CreateGameService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;

/**
 * A handler for creating a game.
 */
public class CreateGameHandler implements Route {
    @Override public Object handle(Request request, Response response) {
        // extract the auth token from the Spark request to create a new request object
        String authToken = request.headers("authorization");

        // deserialize the Spark request to create a new request object
        var gson = new Gson().fromJson(request.body(), Map.class);

        // use the auth token and the game name to parametrize a new request
        CreateGameRequest classRequest = new CreateGameRequest(authToken, (String) gson.get("gameName"));

        // create a result object using a new service object and the request
        CreateGameService classService = new CreateGameService();
        CreateGameResult classResult = classService.createGame(classRequest);

        // set the response code according to the message set by the service
        if (classResult.getMessage()==null) {
            response.status(200);
            return new Gson().toJson(Map.of("gameID", classResult.getGameID()));
        } else if (classResult.getMessage()=="bad request") {
            response.status(400);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else if (classResult.getMessage()=="unauthorized") {
            response.status(401);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        } else {
            response.status(500);
            return new Gson().toJson(Map.of("message", String.format("Error: " + classResult.getMessage())));
        }
    }
}
