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
        System.out.println(request.body());
        Gson gson=new Gson();
        JoinGameRequest joinGameRequest=gson.fromJson(request.body(), JoinGameRequest.class);
        JoinGameService joinGameService=new JoinGameService(joinGameRequest);
        JoinGameResult joinGameResult=joinGameService.joinGame(joinGameRequest);
        if(joinGameResult.getMessage()==null) response.status(200);
        else if(joinGameResult.getMessage()=="Error: bad request") response.status(400);
        else if(joinGameResult.getMessage()=="Error: unauthorized") response.status(401);
        else if(joinGameResult.getMessage()=="Error: already taken") response.status(403);
        else if(joinGameResult.getMessage()=="Error: description") response.status(500);
        return gson.toJson(joinGameResult);
    }
}
