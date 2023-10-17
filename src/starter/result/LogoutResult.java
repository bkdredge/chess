package result;
/**
 * The result of a Logout request.
 */
public class LogoutResult {
    private String message;
    public LogoutResult(){}
    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
}
