package model;

/**
 * A model for a user.
 */
public class User {
    /**
     * The user's username. (String)
     */
    String username = null;

    /**
     * The user's password. (String)
     */
    String password = null;

    /**
     * The user's email. (String)
     */
    String email = null;

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

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    /**
     * A custom hashcode function.
     * @return hash
     */
    @Override public int hashCode() {return super.hashCode();}

    /**
     * A boolean equals function, to compare users.
     * @param o, the object
     * @return true or false
     */
    @Override public boolean equals(Object o) {
        if(o==null) return false; if (o==this) return true;
        if(o.getClass()!=this.getClass()) return false;
        User d = (User) o;
        if(d.getUsername()!=this.getUsername()) return false;
        if(d.getPassword()!=this.getPassword()) return false;
        if(d.getEmail()!=this.getEmail()) return false;
        return true;
    }

    /**
     * A custom toString function
     * @return string
     */
    @Override public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
