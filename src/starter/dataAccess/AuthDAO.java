package dataAccess;

import model.AuthToken;

public class AuthDAO {
    /**
     * An authentication token, represented in the data access object.
     */
    AuthToken authToken = null;

    /**
     *
     * @param thisToken
     * @throws DataAccessException
     */
    void createGame(AuthToken thisToken) throws DataAccessException {
        authToken=thisToken;
    }

    /**
     *
     */
    void insert() {}

    /**
     *
     */
    void find(){}

    /**
     *
     */
    void findAll(){}

    /**
     *
     */
    void claimSpot(){}

    /**
     *
     */
    void updateGame(){}

    /**
     *
     */
    void remove(){}

    /**
     *
     */
    void clear(){}
}
