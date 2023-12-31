package service;

import DAO.AuthDAO;
import DAO.UserDAO;
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
            // call instances of the user and auth token data transfer object.
            var userDao = new UserDAO(); var authDao = new AuthDAO();

            // insert a user into the database
            userDao.insertUserIntoDatabase(new User(request.getUsername(), request.getPassword(), request.getEmail()));
            var authTokenString = UUID.randomUUID().toString();
            var authToken = new AuthToken(authTokenString, request.getUsername());
            authDao.insertAuthTokenIntoDatabase(authToken);

            // return the response with the message set to null (success)
            RegisterResult response = new RegisterResult();
            response.setUsername(request.getUsername());
            response.setAuthToken(authTokenString);
            response.setMessage(null);

            return response;
        }
        catch (DataAccessException e) {
            // return the response with the corresponding error message
            RegisterResult response = new RegisterResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
