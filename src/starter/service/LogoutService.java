package service;

import request.LogoutRequest;
import result.LoginResult;
import result.LogoutResult;
import result.RegisterResult;

/**
 * The service for logging out using a request and result.
 */
public class LogoutService {
    LogoutRequest request;
    public LogoutService(LogoutRequest request) {this.request=request;}
    /**
     * Logs out the user represented by the authToken.
     * @param request
     * @return
     */
    public LogoutResult logout(LogoutRequest request) {
        try {
            return new LogoutResult(null);
        } catch (Exception e) {
            return new LogoutResult("Error: unauthorized");
            //return new LogoutResult("Error: description");
        }
    }
}
