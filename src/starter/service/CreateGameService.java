package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import model.Game;
import request.CreateGameRequest;
import result.CreateGameResult;
import dataAccess.DataAccessException;

/**
 * The service for creating a game using a request and result.
 */
public class CreateGameService {
    public static int IDCounter = 1;

    /**
     * Creates a new game.
     * @param request
     * @return
     */
    public CreateGameResult createGame(CreateGameRequest request) {
        try {
            var tokens = new AuthDAO(); var games = new GameDAO();
            if (!tokens.isAuthTokenInDatabase(request.getAuthToken())) {
                throw new DataAccessException("unauthorized");
            }
            Game game = new Game();
            if ((request.getGameName() == null) || (request.getGameName().isEmpty())) {
                throw new DataAccessException("bad request");
            }
            game.setGameName(request.getGameName());
            game.setGameID(IDCounter);
            games.insertGameIntoDatabase(game);
            var response = new CreateGameResult();
            response.setGameID(IDCounter);
            response.setMessage(null);
            IDCounter++;
            return response;
        }
        catch (DataAccessException e){
            var response = new CreateGameResult();
            response.setMessage(e.getMessage());
            return response;
        }

    }
}
