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
        String authToken = request.headers("authorization");
        var gson = new Gson().fromJson(request.body(), Map.class);
        CreateGameRequest classRequest = new CreateGameRequest(authToken, (String) gson.get("gameName"));
        CreateGameService classService = new CreateGameService();
        CreateGameResult classResult = classService.createGame(classRequest);
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
