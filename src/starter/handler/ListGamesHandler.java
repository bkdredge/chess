package handler;
import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;

import com.google.gson.Gson;
import request.JoinGameRequest;
import request.ListGamesRequest;
import result.JoinGameResult;
import result.ListGamesResult;
import service.JoinGameService;
import service.ListGamesService;
import spark.Request;
import spark.Response;
import spark.Route;


public class ListGamesHandler implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        System.out.println(request.body());
        Gson gson=new Gson();
        ListGamesRequest listGamesRequest=gson.fromJson(request.body(),ListGamesRequest.class);
        ListGamesService listGamesService=new ListGamesService(listGamesRequest);
        ListGamesResult listGamesResult=listGamesService.listGames(listGamesRequest);
        if(listGamesResult.getGames()!=null) {response.status(200);}
        else if(listGamesResult.getMessage()=="Error: unauthorized") response.status(401);
        else if(listGamesResult.getMessage()=="Error: description") response.status(500);
        return gson.toJson(listGamesResult);
    }
}