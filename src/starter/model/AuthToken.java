package model;

import java.util.Objects;

public class AuthToken {
    /**
     * An authentication token. (String)
     */
    String authToken = null;

    /**
     * Username corresponding to token. (String)
     */
    String username = null;

    /**
     * Empty constructor
     */
    public AuthToken(){}

    /**
     * A fully-parametrized constructor.
     */
    public AuthToken(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    /**
     * A getter for the authentication token.
     * @return authToken
     */
    public String getAuthToken() {return authToken;}

    /**
     * A setter for the authentication token.
     * @param authToken
     */
    public void setAuthToken(String authToken) {this.authToken = authToken;}

    /**
     * A getter for the username.
     * @return username
     */
    public String getUsername() {return username;}

    /**
     * A setter for the username.
     * @param username
     */
    public void setUsername(String username) {this.username = username;}

    /**
     * A boolean equals function, to compare authentication tokens.
     * @param o, for the object
     * @return true or false
     */
    @Override public boolean equals(Object o) {
        if(o==null) return false; if (o==this) return true;
        if(o.getClass()!=this.getClass()) return false;
        AuthToken d = (AuthToken) o;
        if(d.getAuthToken()!=this.getAuthToken()) return false;
        if(d.getUsername()!=this.getUsername()) return false;
        return true;
    }

    /**
     * A custom hashcode function.
     * @return hash
     */
    @Override public int hashCode() {return Objects.hash(authToken, username);}

    /**
     * A custom toString function
     * @return string
     */
    @Override public String toString() {
        return "AuthToken{" +
                "authToken='" + authToken + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
