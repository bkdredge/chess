package request;

import java.util.Map;

/**
 * The request to register a new user account.
 */
public class RegisterRequest {
    private String username, password, email;
    public RegisterRequest(Map<String, String> body) {
        setUsername(body.get("username"));
        setEmail(body.get("email"));
        setPassword(body.get("password"));
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}
