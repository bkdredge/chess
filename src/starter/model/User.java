package model;

/**
 * A model for a user.
 */
public class User {
    /**
     * The user's username. (String)
     */
    private String username;

    /**
     * The user's password. (String)
     */
    private String password;

    /**
     * The user's email. (String)
     */
    private String email;

    /**
     * An empty User constructor.
     */
    public User(){}

    /**
     * A fully-parametrized User constructor.
     * @param username
     * @param password
     * @param email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
