package service;

import dataAccess.AuthDAO;
import dataAccess.UserDAO;
import model.AuthToken;
import model.User;
import request.LoginRequest;
import result.LoginResult;
import dataAccess.DataAccessException;

import java.util.UUID;

/**
 * The service for logging in using a request and result.
 */
public class LoginService {

    /**
     * Logs in an existing user (returns a new authToken).
     * @param request
     * @return
     */
    public LoginResult login(LoginRequest request) {
        try {
            // create new instance of userDAO and AuthDAO classes
            UserDAO users = new UserDAO(); AuthDAO tokens = new AuthDAO();

            // retrieve the user from the database whose username matches that of the request
            User user = users.retrieveUserFromDatabase(request.getUsername());

            // check if the password from the user matches that of the request
            if (user.getPassword()!=request.getPassword()) {

                // if not, throw an "unauthorized" error
                throw new DataAccessException("unauthorized");
            }

            // create a new auth token string
            String authTokenString = UUID.randomUUID().toString();

            // create a new auth token from the string and from the username
            AuthToken authToken = new AuthToken(authTokenString, request.getUsername());

            // insert it into the database
            tokens.insertAuthTokenIntoDatabase(authToken);

            // return the response with the message set to null (success)
            LoginResult response = new LoginResult();
            response.setUsername(request.getUsername());
            response.setAuthToken(authTokenString);
            response.setMessage(null);

            return response;
        }
        catch (DataAccessException e) {
            // return the response with the corresponding error message
            LoginResult response = new LoginResult();
            response.setMessage(e.getMessage());
            return response;
        }

    }

}
