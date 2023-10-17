package dataAccess;

import model.User;

import java.util.Collection;

/**
 * Data Access Object for users in the database.
 */
public class UserDAO {

    /**
     * A method for inserting a user into the database.
     * @param newUserInDatabase
     * @throws DataAccessException
     */
    void insertUserIntoDatabase(User newUserInDatabase) throws DataAccessException{}

    /**
     * A method for retrieving a single user from a database with a username.
     * @param usernameInDatabase
     * @return user
     * @throws DataAccessException
     */
    User retrieveUserFromDatabase(String usernameInDatabase) throws DataAccessException{
        return null;
    }

    /**
     * A method for retrieving all the users from a database.
     * @return
     * @throws DataAccessException
     */
    Collection<User> retrieveAllUsersFromDatabase() throws DataAccessException{
        return null;
    }

    /**
     * A method for assigning a user a team.
     * @param userToAssignTeam
     * @throws DataAccessException
     */
    void assignUserTeam(User userToAssignTeam) throws DataAccessException{}

    /**
     * A method for updating the user in the database.
     * @param userInDatabase
     * @throws DataAccessException
     */
    void updateUserInDatabase(User userInDatabase) throws DataAccessException{}

    /**
     * A method for removing a single user from the database.
     * @param userToRemove
     * @throws DataAccessException
     */
    void removeUserFromDatabase(User userToRemove) throws DataAccessException{}

    /**
     * A method for clearing the database of all users.
     * @throws DataAccessException
     */
    void clearAllUsersInDatabase() throws DataAccessException{}
}
