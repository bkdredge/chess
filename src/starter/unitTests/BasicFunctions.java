package unitTests;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import request.*;
import result._Result;
import service.*;

import java.util.HashMap;

public class BasicFunctions {
    public void clearDatabase() {
        // declare instances of all DAOs
        var tokens = new AuthDAO();
        var users = new UserDAO();
        var games = new GameDAO();

        // try to clear all
        try {
            games.clearGamesInDatabase();
            tokens.clearAllTokensInDatabase();
            users.clearAllUsersInDatabase();

            // reset ID counter
            //var reset = new CreateGameService();
            //FIXME reset.IDCounter=0;
        }
        catch (DataAccessException e) {

            // catch a server failure
            System.out.println("Server failure");
        }
    }

    public _Result createAGame(String authToken, String gameName) {
        // return result of request, given a token and gameName
        var createGameRequest = new CreateGameRequest(authToken, gameName);
        var createGameService = new CreateGameService();
        return createGameService.createGame(createGameRequest);
    }

    public _Result joinAGame(String auth, String color, int gameID) {
        // return result of request, given a token, color, and gameID
        var joinGameRequest = new JoinGameRequest(auth, color, gameID);
        var joinGameService = new JoinGameService();
        return joinGameService.joinGame(joinGameRequest);
    }

    public _Result listGames(String auth) {
        // return result of request, given a token
        var listGameRequest = new ListGamesRequest(auth);
        var listGameService = new ListGamesService();
        return listGameService.listGames(listGameRequest);
    }

    public _Result loginUser(String username, String password) {
        // make LoginRequest body
        var body = new HashMap<String, String>();
        body.put("username", username);
        body.put("password", password);

        // return result of request
        var loginRequest = new LoginRequest(body);
        var loginService = new LoginService();
        return loginService.login(loginRequest);
    }

    public _Result logoutUser(String authToken) {
        // return result of request, given a token
        var logoutRequest = new LogoutRequest(authToken);
        var logoutService = new LogoutService();
        return logoutService.logout(logoutRequest);
    }

    public _Result registerUser(String username, String password, String email) {
        // make RegisterRequest body
        var body = new HashMap<String, String>();
        body.put("username", username);
        body.put("password", password);
        body.put("email", email);

        // return result of request
        var registerRequest = new RegisterRequest(body);
        var registerService = new RegisterService();
        return registerService.register(registerRequest);
    }
}
