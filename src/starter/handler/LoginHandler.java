package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import request.LoginRequest;
import result.JoinGameResult;
import result.LoginResult;
import service.JoinGameService;
import service.LoginService;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * A handler for logging in.
 */
public class LoginHandler implements Route{
    public LoginHandler(){}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
