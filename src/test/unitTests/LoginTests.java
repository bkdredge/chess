package unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginTests extends BasicFunctions{
    @BeforeEach
    void clearData() {
        this.clearDatabase();
    }


    @Test
    void testLogin() {
        var regRes = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertNull(regRes.getMessage()); //success

        var loginRes = this.loginUser("Barbie", "bonbons");
        assertNull(loginRes.getMessage()); //success
    }

    @Test
    void testLoginWrongPassword() {
        var regRes = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertNull(regRes.getMessage()); //success

        var loginRes = this.loginUser("Barbie", "ILoveKen");
        assertEquals("unauthorized",loginRes.getMessage());
    }

    @Test
    void testLoginNoPassword() {
        var regRes = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertNull(regRes.getMessage()); //success

        var loginRes = this.loginUser("Oppenheimer", "bombs");
        assertEquals("unauthorized", loginRes.getMessage());
    }

    @Test
    void clearDataAfter() {
        this.clearDatabase();
    }
}
