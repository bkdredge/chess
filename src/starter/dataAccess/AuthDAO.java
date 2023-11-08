package dataAccess;

import database.*;
import model.AuthToken;

import java.sql.Connection;


/**
 * Data Access Object for authentication tokens in the database.
 */
public class AuthDAO {
    /**
     * A temporary database object.
     */
    private Database database = new MySQLDatabase();

    /**
     * A method for inserting a new authentication token into the database.
     * @param newTokenInDatabase
     * @throws DataAccessException
     */
    public void insertAuthTokenIntoDatabase(AuthToken newTokenInDatabase) throws DataAccessException {
        String username = newTokenInDatabase.getUsername();
        String authToken = newTokenInDatabase.getAuthToken();
        if (isAuthTokenUserNull(authToken, username) || isAuthTokenUserEmpty(authToken, username)) {
            throw new DataAccessException("authToken and username must not by null or empty.");
        }
        if (isAuthTokenInDatabase(authToken)) {
            throw new DataAccessException("Cannot have duplicate authTokens.");
        }
        database.createAuthTokenInDatabase(new AuthToken(authToken, username));
    }

    /**
     * A method for retrieving an authentication token from a database with an authToken.
     * @param authToken
     * @throws DataAccessException
     */
    public AuthToken retrieveAuthTokenFromDatabase(String authToken) throws DataAccessException {
        if (isAuthTokenNull(authToken) || isAuthTokenEmpty(authToken)) {
            throw new DataAccessException("authToken can't be empty or null.");
        }
        return database.readAuthTokenInDatabase(authToken);
    }

    /**
     * A method for removing an authentication token from a database with a username.
     * @param authToken
     * @throws DataAccessException
     */
    public void removeAuthTokenFromDatabase(String authToken) throws DataAccessException {
        if (isAuthTokenNull(authToken) || isAuthTokenEmpty(authToken)) {
            throw new DataAccessException("authToken can't be null or empty.");
        }
        if (!isAuthTokenInDatabase(authToken)) {
            throw new DataAccessException("unauthorized");
        }
        database.deleteAuthTokenFromDatabase(authToken);
    }

    /**
     * A method for clearing all authentication tokens in a database.
     * @throws DataAccessException
     */
    public void clearAllTokensInDatabase() {
        database.clearAuthTokensInDatabase();
    }
    public boolean isAuthTokenInDatabase(String authToken) {
        return database.readAuthTokenInDatabase(authToken) != null;
    }
    public boolean isAuthTokenNull(String authToken) {
        return authToken == null;
    }
    public boolean isAuthTokenEmpty(String authToken) {
        return authToken.isEmpty();
    }
    public boolean isAuthTokenUserNull(String authToken, String username) {
        return (isAuthTokenNull(authToken) || (username == null));
    }
    public boolean isAuthTokenUserEmpty(String authToken, String username) {
        return (isAuthTokenNull(authToken) || (username.isEmpty()));
    }
}
