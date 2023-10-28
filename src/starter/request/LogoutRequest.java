package request;

/**
 * The request to log out.
 */
public class LogoutRequest {
    private String authToken;
    public LogoutRequest(String authTokenS) {
        setAuthToken(authTokenS);
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
