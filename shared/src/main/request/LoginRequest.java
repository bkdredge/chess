package request;

import java.util.Map;

/**
 * The request to log in.
 */
public class LoginRequest {

    /**
     * A string to store a username
     */
    private String username;

    /**
     * A string to store a password
     */
    private String password;
    public LoginRequest(Map<String, String> body) {
        setUsername(body.get("username"));
        setPassword(body.get("password"));
    }
    public LoginRequest(String username, String password) {
        setUsername(username);
        setPassword(password);
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
