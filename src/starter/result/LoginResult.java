package result;
/**
 * The result of a Login request.
 */
public class LoginResult {
    private String message;
    private String authToken;
    private String userName;
    public LoginResult(String username, String authToken, String message){
        this.userName = username;
        this.authToken = authToken;

        if(this.userName!=null & this.authToken!=null) this.message=null;
        else{this.message="Error: bad request";}
        // 200 for success plus username and auth
    }
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public String getAuthToken() {return authToken;}
    public void setAuthToken(String authToken) {this.authToken = authToken;}
    public String getUserName() {return userName;}
    //public void setUserName(String userName) {this.userName = userName;}
}
