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
        System.out.println(request.body());
        Gson gson = new Gson();
        LogoutRequest logoutRequest=gson.fromJson(request.body(), LogoutRequest.class);
        LogoutService logoutService=new LogoutService(logoutRequest);
        LogoutResult logoutResult=logoutService.logout(logoutRequest);
        if(logoutResult.getMessage()==null) {response.status(200);}
        else if(logoutResult.getMessage()=="Error: unauthorized") {response.status(401);}
        else if(logoutResult.getMessage()=="Error: description") {response.status(500);}
        return gson.toJson(logoutResult);
    }
}
