package result;

/**
 * The result of a List Games request.
 */
public class ListGamesResult {
    private String message;
    public ListGamesResult(String message){
        this.message=message;
    }
    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
}
