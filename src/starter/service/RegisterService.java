package service;

import dataAccess.AuthDAO;
import dataAccess.UserDAO;
import model.AuthToken;
import model.User;
import request.RegisterRequest;
import result.RegisterResult;
import dataAccess.DataAccessException;

import java.util.*;

/**
 * The service for registering a new user account using a request and result.
 */
public class RegisterService {

    /**
     * Register a new user.
     * @param request
     * @return
     */
    public RegisterResult register(RegisterRequest request) {
        try {
            var userDao = new UserDAO(); var authDao = new AuthDAO();

            userDao.insertUserIntoDatabase(new User(request.getUsername(), request.getPassword(), request.getEmail()));
            var authTokenString = UUID.randomUUID().toString();
            var authToken = new AuthToken(authTokenString, request.getUsername());
            authDao.insertAuthTokenIntoDatabase(authToken);

            var response = new RegisterResult();
            response.setUsername(request.getUsername());
            response.setAuthToken(authTokenString);
            response.setMessage(null);

            return response;
        }
        catch (DataAccessException e) {
            var response = new RegisterResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
