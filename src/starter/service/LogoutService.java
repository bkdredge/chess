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
            var tokens = new AuthDAO();
            tokens.removeAuthTokenFromDatabase(request.getAuthToken());
            var response = new LogoutResult();
            response.setMessage(null);
            return response;
        }
        catch (DataAccessException e) {
            var response = new LogoutResult();
            response.setMessage(e.getMessage());
            return response;
        }
    }
}
