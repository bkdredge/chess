package handler;

import com.google.gson.Gson;
import request.JoinGameRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.JoinGameResult;
import result.LoginResult;
import result.RegisterResult;
import service.JoinGameService;
import service.LoginService;
import service.RegisterService;
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
        System.out.println(request.body());
        Gson gson = new Gson();
        LoginRequest loginRequest = gson.fromJson(request.body(), LoginRequest.class);
        LoginService loginService = new LoginService(loginRequest); //need to fix
        LoginResult loginResult = loginService.login(loginRequest);
        if (loginResult.getUserName() != null && loginResult.getAuthToken() != null) {
            response.status(200);
        }
        else if (loginResult.getMessage() == "Error: unauthorized") {response.status(401);}
        else if (loginResult.getMessage() == "Error: description") {response.status(500);}
        return gson.toJson(loginResult);
    }
}
