package result;

/**
 * The result of a Register request.
 */
public class RegisterResult extends _Result {
    /**
     * A string to store a username.
     */
    private String username;

    /**
     * A string to store an authToken
     */
    private String authToken;
    public RegisterResult() {}
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
