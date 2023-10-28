package service;

import dataAccess.*;
import result.ClearDataResult;
import dataAccess.DataAccessException;

/**
 * The service for clearing data using a request and result.
 */
public class ClearDataService {

    /**
     * Clears the database. Removes all users, games, and authTokens.
     * @return
     */
    public ClearDataResult clear() {
        try {
            var games = new GameDAO();
            var users = new UserDAO();
            var tokens = new AuthDAO();

            games.clearGamesInDatabase();
            users.clearAllUsersInDatabase();
            tokens.clearAllTokensInDatabase();
            return new ClearDataResult();
        }
        catch (DataAccessException e) {
            var response = new ClearDataResult();
            response.setMessage(String.format("Error: %s", e.getMessage()));
            return response;
        }

    }
}
