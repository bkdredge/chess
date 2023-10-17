package request;
import model.AuthToken;

/**
 * The request to log in.
 */
public class LoginRequest {
    private String username;
    private String password;
    public LoginRequest() {}
    AuthToken returnAuthToken(){return null;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
}
