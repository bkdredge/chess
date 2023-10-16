package dataAccess;

import model.AuthToken;

public class AuthDAO {
    /**
     *
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
