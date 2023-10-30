package unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.LoginResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogoutTests extends BasicFunctions{
    @BeforeEach
    void clearData() {
        this.clearDatabase();
    }


    @Test
    void testLogout() {
        var registerResult = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertEquals(registerResult.getMessage(), null); //success

        var loginResult = this.loginUser("Barbie","bonbons");
        assertEquals(loginResult.getMessage(), null); //success

        var logoutResult = this.logoutUser(((LoginResult) loginResult).getAuthToken()); // using same auth token
        assertEquals(logoutResult.getMessage(), null); //success
    }

    @Test
    void testLogoutIncorrectToken() {
        var registerResult = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertEquals(registerResult.getMessage(), null); //success

        var loginResult = this.loginUser("Barbie","bonbons");
        assertEquals(loginResult.getMessage(), null); //success

        var logoutResult = this.logoutUser("weird token"); // using different auth token
        assertEquals(logoutResult.getMessage(), "unauthorized");
    }

    @Test
    void clearDataAfter() {
        this.clearDatabase();
    }
}
