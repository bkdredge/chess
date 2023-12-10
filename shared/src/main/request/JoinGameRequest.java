package request;

/**
 * The request to join a game.
 */
public class JoinGameRequest {

    /**
     * A string to store an auth token
     */
    private String authToken;

    /**
     * A string to store the team color
     */
    private String color;

    /**
     * An integer to store a game ID
     */
    private int gameID;
    public JoinGameRequest(String authToken, String color, int gameID) {
        setAuthToken(authToken);
        setColor(color);
        setGameID(gameID);
    }
    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getGameID() {
        return gameID;
    }
    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
