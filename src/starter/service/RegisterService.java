package service;

import request.RegisterRequest;
import result.RegisterResult;
/**
 * The service for registering a new user account using a request and result.
 */
public class RegisterService {
    RegisterRequest request;
    public RegisterService(RegisterRequest request) {this.request=request;}
    /**
     * Register a new user.
     * @param request
     * @return
     */
    public RegisterResult register(RegisterRequest request) {
        try {
            return new RegisterResult(request.getUsername(), request.getAuthToken(), null);
            //return registerResult;
        } catch (Exception e) {
            return new RegisterResult(null,null,"Error: description");
        }
        /*
        try
            register
            catch ex
            return response()error
            catch ex
         */
    }
}

