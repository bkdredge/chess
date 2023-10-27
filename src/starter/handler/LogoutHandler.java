package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import request.LogoutRequest;
import result.JoinGameResult;
import result.LogoutResult;
import service.JoinGameService;
import service.LogoutService;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * A handler for logging out.
 */
public class LogoutHandler implements Route{
    public LogoutHandler(){}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
