package service;
import request.LoginRequest;
import result.LoginResult;

/**
 * The service for logging in using a request and result.
 */
public class LoginService {
    LoginRequest request;
    public LoginService(LoginRequest request) {this.request=request;}
    /**
     * Logs in an existing user (returns a new authToken).
     * @param request
     * @return
     */
    public LoginResult login(LoginRequest request) {
        try {
            return new LoginResult(request.getUsername(), request.getAuthToken(), null);
            //return registerResult;
        } catch (Exception e) {
            return new LoginResult(null,null,"Error: description");
        }
    }
}
