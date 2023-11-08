package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import database.MemoryDatabase;
import database.MySQLDatabase;
import model.Game;
import request.CreateGameRequest;
import result.CreateGameResult;
import dataAccess.DataAccessException;

/**
 * The service for creating a game using a request and result.
 */
public class CreateGameService {

    /**
     * Creates a new game.
     * @param request
     * @return
     */
    public CreateGameResult createGame(CreateGameRequest request) {
        try {
            // create instances of DAOs for auth tokens and games
            AuthDAO tokens = new AuthDAO(); GameDAO games = new GameDAO();

            // check if the token from the request is in the auth token DAO
            if (!tokens.isAuthTokenInDatabase(request.getAuthToken())) {
                // if not, throw an "unauthorized" error
                throw new DataAccessException("unauthorized");
            }
            // create a game object
            Game game = new Game();

            // check if the name from the game request is empty/null
            if ((request.getGameName() == null) || (request.getGameName().isEmpty())) {
                // if so, throw "bad request" error
                throw new DataAccessException("bad request");
            }

            // set name of game to the game name from the request
            game.setGameName(request.getGameName());
            // set gameID
            game.setGameID(games.getNumberOfGames()+1);
            // insert game into the game DAO
            games.insertGameIntoDatabase(game);

            // return the response with the message set to null (success) and the gameID set to the request's gameID
            CreateGameResult response = new CreateGameResult();
            response.setMessage(null); //success
            response.setGameID(game.getGameID());
            return response;
        }
        catch (DataAccessException e){
            // return response with error
            CreateGameResult response = new CreateGameResult();
            response.setMessage(e.getMessage());
            return response;
        }

    }
}
