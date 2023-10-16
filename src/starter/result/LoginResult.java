package result;

public class LoginResult {
    private String message;
    private String authToken;
    private String userName;
    public LoginResult() {}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public String getAuthToken() {return authToken;}
    public void setAuthToken(String authToken) {this.authToken = authToken;}
    public String getUserName() {return userName;}
    public void setUserName(String userName) {this.userName = userName;}
}
