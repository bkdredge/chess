package result;
import model.Game;
import spark.Request;
import spark.Response;
/**
 * The result of a Create Game request.
 */
public class CreateGameResult {
    private String gameName;
    private String message;
    public CreateGameResult(){}
    public CreateGameResult(String gameName, String message) {
        this.gameName = gameName;
        this.message = message;
    }

    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
    public String getGame() {
        return gameName;
    }

}
