package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import request.JoinGameRequest;
import result.JoinGameResult;
import dataAccess.DataAccessException;

/**
 * The service for joining a game using a request and result.
 */
public class JoinGameService {

    /**
     * 	Verifies that the specified game exists,
     * 	and, if a color is specified,
     * 	adds the caller as the requested color to the game.
     * 	If no color is specified, the user is joined as an observer.
     * 	This request is idempotent.
     * @param request
     * @return
     */
    public JoinGameResult joinGame(JoinGameRequest request) {
        try {
            var tokens = new AuthDAO(); var games = new GameDAO();
            if (!tokens.isAuthTokenInDatabase(request.getAuthToken())) {
                throw new DataAccessException("unauthorized");
            }
            var username = tokens.retrieveAuthTokenFromDatabase(request.getAuthToken()).getUsername();
            if (!games.isGameInDatabase(request.getGameID())) {
                throw new DataAccessException("bad request");
            }
            var game = games.retrieveGameFromDatabase(request.getGameID());
            if (request.getColor()!=null) {
                if (request.getColor().equals("WHITE")) {
                    if (game.getWhiteUsername() != null) {
                        throw new DataAccessException("already taken");
                    } else {
                        game.setWhiteUsername(username);
                        games.updateGameInDatabase(game);
                    }
                } else if (request.getColor().equals("BLACK")) {
                    if (game.getBlackUsername() != null) {
                        throw new DataAccessException("already taken");
                    } else {
                        game.setBlackUsername(username);
                        games.updateGameInDatabase(game);
                    }
                }
            }
            var response = new JoinGameResult();
            response.setMessage(null);
            return response;
        }
        catch (DataAccessException e){
            var response = new JoinGameResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
