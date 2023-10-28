package service;

import dataAccess.AuthDAO;
import dataAccess.UserDAO;
import model.AuthToken;
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
            var users = new UserDAO(); var tokens = new AuthDAO();
            var user = users.retrieveUserFromDatabase(request.getUsername());
            if (!user.getPassword().equals(request.getPassword())) {
                throw new DataAccessException("unauthorized");
            }
            var authTokenString = UUID.randomUUID().toString();
            var authToken = new AuthToken(authTokenString, request.getUsername());
            tokens.insertAuthTokenIntoDatabase(authToken);

            var response = new LoginResult();
            response.setUsername(request.getUsername());
            response.setAuthToken(authTokenString);
            response.setMessage(null);

            return response;
        }
        catch (DataAccessException e) {
            var response = new LoginResult();
            response.setMessage(e.getMessage());
            return response;
        }

    }

}
