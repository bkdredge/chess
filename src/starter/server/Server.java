package server;

import chess.ChessGame;
import chess.ChessGameImpl;
import com.google.gson.Gson;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import handler.*;
import model.AuthToken;
import model.Game;
import model.User;
import request.ClearDataRequest;
import request.CreateGameRequest;
import request.JoinGameRequest;
import result.ClearDataResult;
import result.CreateGameResult;
import result.JoinGameResult;
import service.ClearDataService;
import service.CreateGameService;
import service.JoinGameService;
import spark.*;
import java.util.*;

/**
 * Server class
 */
public class Server {
    UserDAO users = new UserDAO();
    GameDAO games = new GameDAO();
    AuthDAO authTokens = new AuthDAO();

    /**
     * Creates a new server
     *
     * @param args from command line
     */
    public static void main(String[] args)
    {
        new Server().run(8080);
    }

    /**
     * Runs the server
     */
    private void run(int number) {
        System.out.println("Initializing HTTP server..."); Spark.port(number);
        Spark.externalStaticFileLocation("web");
        Spark.get("/", (req, res) -> {
            res.type("text/html");
            return Spark.class.getResourceAsStream("/chess/web/index.html");
        });
        System.out.println("You have accessed a server successfully! You get a cookie.");


        /*
        Spark.delete("/db",         this::ClearApplicationHandler); //fixed
        Spark.post("/user",         new RegisterHandler());
        Spark.post("/session",      new LoginHandler());
        Spark.delete("/session",    new LogoutHandler());
        Spark.get("/game",          new ListGamesHandler());
        Spark.post("/game",         this::CreateGameHandler); //fixed
        Spark.put("/game",          this::JoinGamesHandler);
        */

        /*
        Spark.delete("/db", new ClearDataHandler());
        Spark.post("/user", new RegisterHandler());
        Spark.post("/session", new LoginHandler());
        Spark.delete("/session", new LogoutHandler());
        Spark.get("/game", new ListGamesHandler());
        Spark.post("/game", new CreateGameHandler());
        Spark.put("/game", new JoinGameHandler());
         */
    }

    // IN-SERVER HANDLERS
    private Object ClearApplicationHandler(Request request, Response result) throws DataAccessException{
        System.out.println(request.body());
        Gson gson=new Gson();
        ClearDataRequest clearDataRequest=gson.fromJson(request.body(), ClearDataRequest.class);
        ClearDataService clearDataService=new ClearDataService(clearDataRequest);
        ClearDataResult clearDataResult=clearDataService.clear(clearDataRequest);
        if(clearDataResult.getMessage()==null) {
            result.status(200);
            //
            authTokens.clearAllTokensInDatabase();
            games.clearGamesInDatabase();
            users.clearAllUsersInDatabase();
            //
        }
        else {result.status(500);}
        return gson.toJson(clearDataResult);
    }

    public Object CreateGameHandler(Request request, Response response) throws DataAccessException {
        System.out.println(request.body());
        Gson gson=new Gson();
        CreateGameRequest createGameRequest=gson.fromJson(request.body(),CreateGameRequest.class);
        CreateGameService createGameService=new CreateGameService(createGameRequest);
        CreateGameResult createGameResult=createGameService.createGame(createGameRequest);
        if(createGameResult.getMessage()==null) {
            response.status(200);
            //
            games.insertGameIntoDatabase(new Game(
                    Integer.parseInt(request.queryParams("gameID")),
                    request.queryParams("whiteUsername"),
                    request.queryParams("blackUsername"),
                    request.queryParams("gameName"),
                    new ChessGameImpl()
            ));
            //Game(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game)
            //
        }
        else if(createGameResult.getMessage()=="Error: bad request") {response.status(400);}
        else if(createGameResult.getMessage()=="Error: authorized") {response.status(401);}
        else {response.status(500);} //description
        return gson.toJson(createGameResult);
    }

    public Object JoinGamesHandler(Request request, Response response) throws DataAccessException {
        System.out.println(request.body());
        Gson gson=new Gson();
        JoinGameRequest joinGameRequest=gson.fromJson(request.body(), JoinGameRequest.class);
        JoinGameService joinGameService=new JoinGameService(joinGameRequest);
        JoinGameResult joinGameResult=joinGameService.joinGame(joinGameRequest);
        if(joinGameResult.getMessage()==null) {
            response.status(200);
            //
            //String username, int gameID, String color
            games.assignTeamInGame(
                    // get the authToken from the request headers
                    // get the username from the auth token
                    authTokens.retrieveAuthTokenFromDatabase(request.headers("authorization")).getUsername(),
                    Integer.parseInt(request.queryParams("gameID")),
                    request.queryParams("playerColor")
                    );
            //
        }
        else if(joinGameResult.getMessage()=="Error: bad request") response.status(400);
        else if(joinGameResult.getMessage()=="Error: unauthorized") response.status(401);
        else if(joinGameResult.getMessage()=="Error: already taken") response.status(403);
        else if(joinGameResult.getMessage()=="Error: description") response.status(500);
        return gson.toJson(joinGameResult);
    }

    // END OF IN-SERVER HANDLERS

    /*
    // THESE ARE THE DATABASE OPERATIONS
    void clearApplication() throws DataAccessException {
        users.clearAllUsersInDatabase();
        games.clearGamesInDatabase();
        authTokens.clearAllTokensInDatabase();
    } //in the service method
    void registerNewUser(User user) {
        users.add(user);
        AuthToken newToken = new AuthToken(Integer.toString(authTokens.size()),user.getUsername());
        authTokens.add(newToken);
    }
    void loginExistingUser(User user) {
        if(users.contains(user)) {
            AuthToken newToken = new AuthToken(Integer.toString(authTokens.size()),user.getUsername());
            authTokens.add(newToken);
        }
    }
    void logoutUser(User user) {
        if(users.contains(user)) {
            for(var token:authTokens) {
                if(token.getUsername()==user.getUsername()) {
                    authTokens.remove(token);
                }
            }
        }
    }
    Collection<Game> listAllGames() {return games;}
    void createNewGame(Game game) {games.add(game);}
    void joinGame(User user, ChessGame.TeamColor color, Game game) {
        if(users.contains(user) && games.contains(game)) {
            if(color==ChessGame.TeamColor.BLACK) {
                game.setBlackUsername(user.getUsername());
            } else if(color== ChessGame.TeamColor.WHITE) {
                game.setWhiteUsername(user.getUsername());
            } else {
                //
            }
        }
    }
    // END TEMPORARY DB OPERATIONS

     */
}