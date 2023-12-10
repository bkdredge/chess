package result;

import request.ResponseObject;

/**
 * The result of a Logout request.
 */
public class LogoutResult extends _Result {
    public LogoutResult() {}
    public LogoutResult(ResponseObject statusCode){
        this.setCode(statusCode);
        this.setMessage(null);
    }
    public LogoutResult(ResponseObject statusCode, String errorMessage){
        this.setCode(statusCode);
        this.setMessage(errorMessage);
    }
}
