package handler;
import com.google.gson.Gson;
import request.ClearDataRequest;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * A handler for clearing data.
 */
public class ClearDataHandler implements Route{
    public ClearDataHandler(){}
    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
