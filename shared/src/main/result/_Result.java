package result;

import request.ResponseObject;

/**
 * The result of a general request.
 * This holds the message for each kind.
 */
public class _Result {
    /**
     * A string to store the response message
     */
    private String message;

    private ResponseObject code;
    public void setMessage(String message) {this.message = message;}
    public String getMessage() {return this.message;}
    public void setCode(ResponseObject code) {
        this.code = code;
    }
    public ResponseObject getCode() {return code;}
}
