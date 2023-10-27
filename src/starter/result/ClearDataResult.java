package result;
/**
 * The result of a Clear Data request.
 */
public class ClearDataResult {
    // If we are using Spark, is this necessary?
    private String message;
    public ClearDataResult(){}
    public String getMessage() {return message;}
    public void setMessage(String message) {
        this.message = message;
    }
}
