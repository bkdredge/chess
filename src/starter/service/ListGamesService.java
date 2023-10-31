package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import model.Game;
import request.ListGamesRequest;
import result.ListGamesResult;
import dataAccess.DataAccessException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that lists the current games on the server.
 */
public class ListGamesService {

    /**
     * Gives a list of all games.
     *
     * @param request A ListGamesServiceRequest object.
     * @return A ListGamesServiceResponse object.
     */
    public ListGamesResult listGames(ListGamesRequest request) {
        try {
            // create new instance of AuthDAO and GameDAO classes
            AuthDAO tokens = new AuthDAO(); var games = new GameDAO();

            // check if the auth token from the request is in the auth DAO
            if (!tokens.isAuthTokenInDatabase(request.getAuthToken())) {
                // if not, throw unauthorized error
                throw new DataAccessException("unauthorized");
            }

            // create an object (Collection) from all of the games in the DAO
            ArrayList<Game> allGames = games.retrieveAllGamesFromDatabase();

            // create an array list of hashmaps of strings and objects from the array list of games
            ArrayList<HashMap<String, Object>> listOfGames = new ArrayList<HashMap<String, Object>>();

            // for each game in the array list, add the corresponding information to the hashmaps;
            //   these will display properly
            for (Game game: allGames) {
                var gameInfo = new HashMap<String, Object>();
                gameInfo.put("gameID", game.getGameID());
                gameInfo.put("whiteUsername",game.getWhiteUsername());
                gameInfo.put("blackUsername",game.getBlackUsername());
                gameInfo.put("gameName", game.getGameName());
                listOfGames.add(gameInfo);
            }

            // return the response with the message set to null (success)
            ListGamesResult response = new ListGamesResult();
            response.setGames(listOfGames);
            response.setMessage(null);
            return response;
        }
        catch (DataAccessException e) {
            // return the response with the corresponding error message
            ListGamesResult response = new ListGamesResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
