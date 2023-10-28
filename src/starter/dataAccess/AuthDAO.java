package dataAccess;

import model.AuthToken;

import java.util.Collection;
import java.util.HashSet;

/**
 * Data Access Object for authentication tokens in the database.
 */
public class AuthDAO {
    private Collection<AuthToken>authTokens = new HashSet<AuthToken>();
    /**
     * A method for inserting a new authentication token into the database.
     * @param newTokenInDatabase
     * @throws DataAccessException
     */
    public void insertAuthTokenIntoDatabase(AuthToken newTokenInDatabase) throws DataAccessException {
        authTokens.add(newTokenInDatabase);
    }

    public void insertAuthTokenIntoDatabaseManual(String token, String name) throws DataAccessException {
        authTokens.add(new AuthToken(token,name));
    }

    /**
     * A method for retrieving an authentication token from a database with a username.
     * @param username
     * @return null
     * @throws DataAccessException
     */
    public AuthToken retrieveAuthTokenFromDatabaseUser(String username) throws DataAccessException {
        for(var token:authTokens) {
            if(token.getUsername()==username) {
                return token;
            }
        }
        return null;
    }

    public AuthToken retrieveAuthTokenFromDatabase(String authToken) throws DataAccessException {
        for(var token:authTokens) {
            if(token.getAuthToken()==authToken) {
                return token;
            }
        }
        return null;
    }

    /**
     * A method for retrieving all authentication tokens from a database.
     * @return null
     * @throws DataAccessException
     */
    public Collection<AuthToken> retrieveAllTokensFromDatabase() throws DataAccessException{
        return authTokens;
    }

    /**
     * A method for removing an authentication token from a database with a username.
     * @param username
     * @return null
     * @throws DataAccessException
     */
    public void removeAuthTokenFromDatabase(String username) throws DataAccessException {
        for(var token:authTokens) {
            if(token.getUsername()==username) {
                authTokens.remove(token);
            }
        }
    }

    /**
     * A method for clearing all authentication tokens in a database.
     * @return null
     * @throws DataAccessException
     */
    public void clearAllTokensInDatabase() throws DataAccessException{authTokens.clear();}

}
