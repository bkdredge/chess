package dataAccess;

import model.User;

public class UserDAO {
    /**
     * A user, represented in the data access object.
     */
    User user = null;

    /**
     *
     * @param newUser
     * @throws DataAccessException
     */
    void createUser(User newUser) throws DataAccessException {
        user=newUser;
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
