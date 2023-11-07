package dataAccess;

import database.*;
import model.User;

/**
 * Data Access Object for users in the database.
 */
public class UserDAO {
    /**
     * A temporary database object.
     */
    private Database database = new MySQLDatabase();

    /**
     * A method for inserting a user into the database.
     * @param newUserInDatabase
     * @throws DataAccessException
     */
    public void insertUserIntoDatabase(User newUserInDatabase) throws DataAccessException {
        String username = newUserInDatabase.getUsername();
        String password = newUserInDatabase.getPassword();
        String email = newUserInDatabase.getEmail();
        if (isUsernamePasswordEmailNull(username, password, email) || isUsernamePasswordEmailEmpty(username, password, email)) {
            throw new DataAccessException("bad request");
        }
        if (isUserInDatabase(username)) {
            throw new DataAccessException("already taken");
        }
        database.createUserInDatabase(new User(username, password, email));
    }

    /**
     * A method for retrieving a single user from a database with a username.
     * @param usernameInDatabase
     * @return user
     * @throws DataAccessException
     */
    public User retrieveUserFromDatabase(String usernameInDatabase) throws DataAccessException {
        if (isUserNull(usernameInDatabase) || isUserEmpty(usernameInDatabase)) {
            throw new DataAccessException("username can't be null or empty.");
        }
        if (!isUserInDatabase(usernameInDatabase)) {
            throw new DataAccessException("unauthorized");
        }
        return database.readUserInDatabase(usernameInDatabase);
    }

    /**
     * A method for updating the user in the database.
     * @param username
     * @param password
     * @param email
     * @throws DataAccessException
     */
    public void updateUserInDatabase(String username, String password, String email) throws DataAccessException {
        if (isUsernamePasswordEmailNull(username, password, email) || isUsernamePasswordEmailEmpty(username, password, email)) {
            throw new DataAccessException("Must provide a username, password and email.");
        }
        if (!isUserInDatabase(username)) {
            throw new DataAccessException("This user isn't in the DB.");
        }
        else {
            database.updateUserInDatabase(new User(username, password, email));
        }
    }

    /**
     * A method for removing a single user from the database.
     * @param userToRemoveUsername (username of user to remove)
     * @throws DataAccessException
     */
    public void removeUserFromDatabase(String userToRemoveUsername) throws DataAccessException{
        if (isUserNull(userToRemoveUsername) || isUserEmpty(userToRemoveUsername)) {
            throw new DataAccessException("username can't be null or empty.");
        }
        database.deleteUserFromDatabase(userToRemoveUsername);
    }

    public void clearAllUsersInDatabase() {
        database.clearUsersInDatabase();
    }
    public boolean isUserInDatabase(String username) {
        return database.readUserInDatabase(username) != null;
    }
    public boolean isUserNull(String username) {
        return username == null;
    }
    public boolean isUserEmpty(String username) {
        return username.isEmpty();
    }
    public boolean isUsernamePasswordEmailNull(String username, String password, String email) {
        return (isUserNull(username) || (password == null) || (email == null));
    }
    public boolean isUsernamePasswordEmailEmpty(String username, String password, String email) {
        return (isUserEmpty(username) || (password.isEmpty()) || (email.isEmpty()));
    }
}
