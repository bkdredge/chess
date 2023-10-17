package dataAccess;

import model.AuthToken;

import java.util.Collection;

public class AuthDAO {
    /**
     * A method for inserting a new authentication token into the database.
     * @param newTokenInDatabase
     * @throws DataAccessException
     */
    void insertAuthTokenIntoDatabase(AuthToken newTokenInDatabase) throws DataAccessException {}

    /**
     * A method for retrieving an authentication token from a database with a username.
     * @param username
     * @return null
     * @throws DataAccessException
     */
    AuthToken retrieveAuthTokenFromDatabase(String username) throws DataAccessException {
        return null;
    }

    /**
     * A method for retrieving all authentication tokens from a database.
     * @return null
     * @throws DataAccessException
     */
    Collection<AuthToken> retrieveAllTokensFromDatabase() throws DataAccessException{
        return null;
    }

    /**
     * A method for removing an authentication token from a database with a username.
     * @param username
     * @return null
     * @throws DataAccessException
     */
    void removeAuthTokenFromDatabase(String username) throws DataAccessException {}

    /**
     * A method for clearing all authentication tokens in a database.
     * @return null
     * @throws DataAccessException
     */
    void clearAllTokensInDatabase() throws DataAccessException{}

}
