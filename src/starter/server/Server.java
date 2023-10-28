package server;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import handler.*;
import spark.*;

public class Server {
    UserDAO users = new UserDAO();
    GameDAO games = new GameDAO();
    AuthDAO authTokens = new AuthDAO();

    public static void main(String[] args)
    {
        new Server().run(8080);
    }

    private void run(int number) {
        Spark.port(number);
        Spark.externalStaticFileLocation("web");

        System.out.println("You have accessed a server successfully! You get a cookie.");

        //IN-SERVER HANDLER CLASSES
        Spark.delete("/db",         this::ClearApplicationHandler);
        Spark.post("/user",         this::RegisterNewUserHandler);
        Spark.post("/session",      this::LoginExistingUserHandler);
        Spark.delete("/session",    this::LogoutUserHandler);
        Spark.get("/game",          this::ListAllGamesHandler);
        Spark.post("/game",         this::CreateGameHandler);
        Spark.put("/game",          this::JoinGameHandler);
    }

    // OUT-OF-SERVER HANDLERS
    public Object ClearApplicationHandler(Request request, Response response) throws DataAccessException{
        var handler=new ClearDataHandler();
        return handler.handle(request,response);
    }
    public Object RegisterNewUserHandler(Request request, Response response) throws DataAccessException{
        var handler=new RegisterHandler();
        return handler.handle(request,response);
    }
    public Object LoginExistingUserHandler(Request request, Response response) throws DataAccessException{
        var handler=new LoginHandler();
        return handler.handle(request,response);
    }
    public Object LogoutUserHandler(Request request, Response response) throws DataAccessException{
        var handler=new LogoutHandler();
        return handler.handle(request,response);
    }
    public Object ListAllGamesHandler(Request request, Response response) throws DataAccessException {
        var handler=new ListGamesHandler();
        return handler.handle(request,response);
    }
    public Object CreateGameHandler(Request request, Response response) throws DataAccessException {
        var handler=new CreateGameHandler();
        return handler.handle(request,response);
    }
    public Object JoinGameHandler(Request request, Response response) throws DataAccessException {
        var handler=new JoinGameHandler();
        return handler.handle(request,response);
    }
}