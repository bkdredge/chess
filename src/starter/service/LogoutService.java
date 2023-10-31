package service;

import dataAccess.AuthDAO;
import request.LogoutRequest;
import result.LogoutResult;
import dataAccess.DataAccessException;

/**
 * The service for logging out using a request and result.
 */
public class LogoutService {

    /**
     * Logs out the user represented by the authToken.
     * @param request
     * @return
     */
    public LogoutResult logout(LogoutRequest request) {
        try {
            // create new instance of the AuthDAO class
            AuthDAO tokens = new AuthDAO();

            // remove the token from the auth token DAO which matches that of the request
            tokens.removeAuthTokenFromDatabase(request.getAuthToken());

            // return the response with the message set to null (success)
            LogoutResult response = new LogoutResult();
            response.setMessage(null);
            return response;
        }
        catch (DataAccessException e) {
            // return the response with the corresponding error message
            LogoutResult response = new LogoutResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
