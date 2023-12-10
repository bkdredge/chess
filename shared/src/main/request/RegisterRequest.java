package request;

import java.util.Map;

/**
 * The request to register a new user account.
 */
public class RegisterRequest {
    /**
     * A string to store a username
     */
    private String username;

    /**
     * A string to store a password
     */
    private String password;

    /**
     * A string to store an email
     */
    private String email;
    public RegisterRequest(Map<String, String> body) {
        setUsername(body.get("username"));
        setEmail(body.get("email"));
        setPassword(body.get("password"));
    }
    public RegisterRequest(String username, String email, String password) {
        setUsername(username);
        setEmail(email);
        setPassword(password);
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
