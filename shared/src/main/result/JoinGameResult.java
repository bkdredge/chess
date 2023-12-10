package result;

import request.ResponseObject;

/**
 * The result of a Join Game request.
 */
public class JoinGameResult extends _Result {
    public JoinGameResult() {}
    public JoinGameResult(ResponseObject statusCode, String errorMessage){
        this.setCode(statusCode);
        this.setMessage(errorMessage);
    }
}
