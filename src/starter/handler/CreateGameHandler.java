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
        return null;
    }
}
