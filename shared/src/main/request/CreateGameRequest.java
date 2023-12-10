package request;

/**
 * The request to create a game.
 */
public class CreateGameRequest {

    /**
     * A string to store an auth token
     */
    private String authToken;

    /**
     * A string to store a game name
     */
    private String gameName;
    public CreateGameRequest(String authToken, String gameName) {
        setAuthToken(authToken);
        setGameName(gameName);
    }
    public String getAuthToken() {return authToken;}
    public void setAuthToken(String authToken) {this.authToken = authToken;}
    public String getGameName() {return gameName;}
    public void setGameName(String gameName) {this.gameName = gameName;}
}
