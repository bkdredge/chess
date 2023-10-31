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
            // create instances of all DAOs
            GameDAO games = new GameDAO();
            UserDAO users = new UserDAO();
            AuthDAO tokens = new AuthDAO();

            // clear each DAO
            games.clearGamesInDatabase();
            users.clearAllUsersInDatabase();
            tokens.clearAllTokensInDatabase();

            // return result
            return new ClearDataResult();
        }
        catch (DataAccessException e) {
            // catch and return error
            ClearDataResult response = new ClearDataResult();
            response.setMessage(String.format("Error: %s", e.getMessage()));
            return response;
        }

    }
}
