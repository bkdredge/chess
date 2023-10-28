package handler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import request.CreateGameRequest;
import result.CreateGameResult;
import service.CreateGameService;
import spark.Request;
import spark.Response;
import spark.Route;


/**
 * A handler for creating a game.
 */
public class CreateGameHandler implements Route{
    public CreateGameHandler(){}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println(request.body());
        Gson gson=new Gson();
        CreateGameRequest createGameRequest=gson.fromJson(request.body(),CreateGameRequest.class);
        CreateGameService createGameService=new CreateGameService(createGameRequest);
        CreateGameResult createGameResult=createGameService.createGame(createGameRequest);
        if(createGameResult.getMessage()==null) {response.status(200);}
        else if(createGameResult.getMessage()=="Error: bad request") {response.status(400);}
        else if(createGameResult.getMessage()=="Error: authorized") {response.status(401);}
        else {response.status(500);} //description
        return gson.toJson(createGameResult);
    }
}
