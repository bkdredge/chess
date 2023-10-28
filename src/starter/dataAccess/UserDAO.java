package dataAccess;

import model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Data Access Object for users in the database.
 */
public class UserDAO {
    private Collection<User> users = new HashSet<User>();

    /**
     * A method for inserting a user into the database.
     * @param newUserInDatabase
     * @throws DataAccessException
     */
    public void insertUserIntoDatabase(User newUserInDatabase) throws DataAccessException{
        users.add(newUserInDatabase);
    }



    /**
     * A method for retrieving a single user from a database with a username.
     * @param usernameInDatabase
     * @return user
     * @throws DataAccessException
     */
    public User retrieveUserFromDatabase(String usernameInDatabase) throws DataAccessException{
        for(var user:users) {
            if(user.getUsername()==usernameInDatabase) {
                return user;
            }
        }
        return null;
    }

    /**
     * A method for retrieving all the users from a database.
     * @return
     * @throws DataAccessException
     */
    public Collection<User> retrieveAllUsersFromDatabase() throws DataAccessException{
        return users;
    }

    /**
     * A method for assigning a user a team.
     * @param userToAssignTeam
     * @throws DataAccessException
     */
    /*
    public void assignUserTeam(User userToAssignTeam) throws DataAccessException{
        for(var user:users) {
            if(user.getUsername()== userToAssignTeam.getUsername()) {

            }
        }
    }

     */

    /**
     * A method for updating the user in the database.
     * @param userInDatabase
     * @throws DataAccessException
     */
    public void updateUserInDatabase(User userInDatabase) throws DataAccessException{
        for(var user:users) {
            if(user.getUsername()== userInDatabase.getUsername()) {
                user=userInDatabase;
            }
        }
    }

    /**
     * A method for removing a single user from the database.
     * @param userToRemove
     * @throws DataAccessException
     */
    public void removeUserFromDatabase(User userToRemove) throws DataAccessException{
        for(var user:users) {
            if(user.getUsername()== userToRemove.getUsername()) {
                users.remove(user);
            }
        }
    }

    /**
     * A method for clearing the database of all users.
     * @throws DataAccessException
     */
    public void clearAllUsersInDatabase() throws DataAccessException{users.clear();}
}
