package model;

/**
 * A model for an authentication token.
 */
public class AuthToken {
    /**
     * An authentication token. (String)
     */
    private String authToken;

    /**
     * Username corresponding to token. (String)
     */
    private String username;
    /**
     * An empty AuthToken constructor
     */
    public AuthToken(){}

    /**
     * A fully-parametrized AuthToken constructor.
     */
    public AuthToken(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
