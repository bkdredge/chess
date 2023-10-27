package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import result.JoinGameResult;
import service.JoinGameService;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * A handler for joining a game
 */
public class JoinGameHandler implements Route {
    public JoinGameHandler(){}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
