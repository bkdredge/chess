package result;

/**
 * The result of a Login request.
 */
public class LoginResult extends _Result {

    /**
     * A string to store an authToken
     */
    private String authToken;

    /**
     * A string to store a username
     */
    private String username;
    public LoginResult() {}
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAuthToken() {return authToken;}
    public String getUsername() {return username;}
}
