package request;

import java.util.Map;

/**
 * The request to log in.
 */
public class LoginRequest {
    private String username;
    private String password;
    public LoginRequest(Map<String, String> body) {
        setUsername(body.get("username"));
        setPassword(body.get("password"));
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
