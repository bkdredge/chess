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
        ListGamesRequest classRequest = new ListGamesRequest(request.headers("authorization"));
        ListGamesService classService = new ListGamesService();
        ListGamesResult classResult = classService.listGames(classRequest);
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
