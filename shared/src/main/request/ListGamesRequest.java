package request;


/**
 * The request to list all the games in the database.
 */
public class ListGamesRequest {

    /**
     * A string to store an auth token
     */
    private String authToken;
    public ListGamesRequest(String authToken) {
        setAuthToken(authToken);
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
