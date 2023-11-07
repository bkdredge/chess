package unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.LoginResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LogoutTests extends BasicFunctions{
    @BeforeEach
    void clearData() {
        this.clearDatabase();
    }


    @Test
    void testLogout() {
        var registerResult = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertNull(registerResult.getMessage()); //success

        var loginResult = this.loginUser("Barbie","bonbons");
        assertNull(loginResult.getMessage()); //success

        var logoutResult = this.logoutUser(((LoginResult) loginResult).getAuthToken()); // using same auth token
        assertNull(logoutResult.getMessage()); //success
    }

    @Test
    void testLogoutIncorrectToken() {
        var registerResult = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertNull(registerResult.getMessage()); //success

        var loginResult = this.loginUser("Barbie","bonbons");
        assertNull(loginResult.getMessage()); //success

        var logoutResult = this.logoutUser("weird token"); // using different auth token
        assertEquals("unauthorized", logoutResult.getMessage());
    }

    @Test
    void clearDataAfter() {
        this.clearDatabase();
    }
}
