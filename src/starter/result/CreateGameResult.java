package result;
import model.Game;

/**
 * The result of a Create Game request.
 */
public class CreateGameResult {
    private String message;
    public CreateGameResult(){}
    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
}
