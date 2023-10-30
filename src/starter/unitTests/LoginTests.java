package unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTests extends BasicFunctions{
    @BeforeEach
    void clearData() {
        this.clearDatabase();
    }


    @Test
    void testLogin() {
        var regRes = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertEquals(regRes.getMessage(), null); //success

        var loginRes = this.loginUser("Barbie", "bonbons");
        assertEquals(loginRes.getMessage(), null); //success
    }

    @Test
    void testLoginWrongPassword() {
        var regRes = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertEquals(regRes.getMessage(), null); //success

        var loginRes = this.loginUser("Barbie", "ILoveKen");
        assertEquals(loginRes.getMessage(), "unauthorized");
    }

    @Test
    void testLoginNoPassword() {
        var regRes = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        assertEquals(regRes.getMessage(), null); //success

        var loginRes = this.loginUser("Oppenheimer", "bombs");
        assertEquals(loginRes.getMessage(), "unauthorized");
    }

    @Test
    void clearDataAfter() {
        this.clearDatabase();
    }
}
