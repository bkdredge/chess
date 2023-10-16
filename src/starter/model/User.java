package model;

public class User {
    /**
     *
     */
    String username = null;

    /**
     *
     */
    String password = null;

    /**
     *
     */
    String email = null;

    /**
     *
     */
    public User(){}

    /**
     *
     * @param username
     * @param password
     * @param email
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getUsername() {return username;}

    /**
     *
     * @param username
     */
    public void setUsername(String username) {this.username = username;}

    /**
     *
     * @return
     */
    public String getPassword() {return password;}

    /**
     *
     * @param password
     */
    public void setPassword(String password) {this.password = password;}

    /**
     *
     * @return
     */
    public String getEmail() {return email;}

    /**
     *
     * @param email
     */
    public void setEmail(String email) {this.email = email;}

    /**
     *
     * @return
     */
    @Override public int hashCode() {return super.hashCode();}

    /**
     *
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
     *
     * @return
     */
    @Override public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
