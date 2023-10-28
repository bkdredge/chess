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
        String authToken = request.headers("authorization");
        var gson = new Gson().fromJson(request.body(), Map.class);
        String color = null;
        if (gson.size() > 1) {color = (String) gson.get("playerColor");}
        JoinGameRequest classRequest = new JoinGameRequest(authToken, color, ((Double) gson.get("gameID")).intValue());
        JoinGameService classService = new JoinGameService();
        JoinGameResult classResult = classService.joinGame(classRequest);
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
