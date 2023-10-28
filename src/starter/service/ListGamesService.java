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
            var tokens = new AuthDAO(); var games = new GameDAO();
            if (!tokens.isAuthTokenInDatabase(request.getAuthToken())) {
                throw new DataAccessException("unauthorized");
            }
            var allGames = games.retrieveAllGamesFromDatabase();
            var listOfGames = new ArrayList<HashMap<String, Object>>();
            for (Game game: allGames) {
                var gameInfo = new HashMap<String, Object>();
                gameInfo.put("gameID", game.getGameID());
                gameInfo.put("whiteUsername",game.getWhiteUsername());
                gameInfo.put("blackUsername",game.getBlackUsername());
                gameInfo.put("gameName", game.getGameName());
                listOfGames.add(gameInfo);
            }
            var response = new ListGamesResult();
            response.setGames(listOfGames);
            response.setMessage(null);
            return response;
        }
        catch (DataAccessException e) {
            var response = new ListGamesResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
