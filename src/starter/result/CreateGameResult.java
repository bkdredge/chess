package result;
import model.Game;
import spark.Request;
import spark.Response;
/**
 * The result of a Create Game request.
 */
public class CreateGameResult {
    private int gameID;
    private String message;
    public CreateGameResult(){}
    public CreateGameResult(int gameID, String message) {
        this.gameID = gameID;
        this.message = message;
    }

    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
