package result;

/**
 * The result of a Register request.
 */
public class RegisterResult {
    /**
     * A string to store a username.
     */
    private String username;
    /**
     * A string to store an authToken
     */
    private String authToken;
    /**
     * A string for failure messages
     */
    private String message;
    public RegisterResult(String username, String authToken, String message){
        this.username = username;
        this.authToken = authToken;

        if(this.username!=null & this.authToken!=null) this.message=null;
        else{this.message="Error: bad request";}
        // 200 for success plus username and auth
    }
    //public RegisterResult(String message){this.message = message;}
    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getAuthToken() {return authToken;}
    public void setAuthToken(String authToken) {this.authToken = authToken;}
}
