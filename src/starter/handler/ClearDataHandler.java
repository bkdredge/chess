package handler;
import com.google.gson.Gson;
import request.ClearDataRequest;
import result.ClearDataResult;
import service.ClearDataService;
import spark.Request;
import spark.Response;
import spark.Route;
import dataAccess.*;

import server.Server;
/**
 * A handler for clearing data.
 */
public class ClearDataHandler implements Route{
    public ClearDataHandler(){}
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println(request.body());
        Gson gson=new Gson();
        ClearDataRequest clearDataRequest=gson.fromJson(request.body(), ClearDataRequest.class);
        ClearDataService clearDataService=new ClearDataService(clearDataRequest);
        ClearDataResult clearDataResult=clearDataService.clear(clearDataRequest);
        if(clearDataResult.getMessage()==null) {
            response.status(200);
        }
        else {response.status(500);}
        return gson.toJson(clearDataResult);
    }
}
