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
        return null;
    }
}