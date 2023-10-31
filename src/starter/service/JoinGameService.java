package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import model.Game;
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
            // create new instance of AuthDAO and GameDAO classes
            AuthDAO tokens = new AuthDAO(); GameDAO games = new GameDAO();

            // check if the auth token from the request is in the auth DAO
            if (!tokens.isAuthTokenInDatabase(request.getAuthToken())) {
                // if not, throw unauthorized error
                throw new DataAccessException("unauthorized");
            }
            // create a username from the validated auth token
            String username = tokens.retrieveAuthTokenFromDatabase(request.getAuthToken()).getUsername();

            // check if the game is in the database
            if (!games.isGameInDatabase(request.getGameID())) {
                // if not, throw bad request error
                throw new DataAccessException("bad request");
            }

            // create a game
            Game game = games.retrieveGameFromDatabase(request.getGameID());

            // if the color is not null
            if (request.getColor()!=null) {

                // and if the color is white
                if (request.getColor().equals("WHITE")) {

                    // and if there is already a user assigned to team white
                    if (game.getWhiteUsername() != null) {

                        // throw an "already taken" exception
                        throw new DataAccessException("already taken");
                    } else {

                        // if not, set the username to team white and update the game
                        game.setWhiteUsername(username);
                        games.updateGameInDatabase(game);
                    }

                    //same process if the color is black
                } else if (request.getColor().equals("BLACK")) {
                    if (game.getBlackUsername() != null) {
                        throw new DataAccessException("already taken");
                    } else {
                        game.setBlackUsername(username);
                        games.updateGameInDatabase(game);
                    }
                }
            }
            // return the response with the message set to null (success)
            JoinGameResult response = new JoinGameResult();
            response.setMessage(null);
            return response;
        }
        catch (DataAccessException e){
            // return the response with the corresponding error message
            JoinGameResult response = new JoinGameResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
