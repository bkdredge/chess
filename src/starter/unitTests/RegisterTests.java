package unitTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RegisterTests extends BasicFunctions{
    @Test
    void registerUser() {
        this.clearDatabase();

        var registerResult = this.registerUser("Barbie","bonbons","barbie@gmail.com");
        assertNull(registerResult.getMessage()); //success

        var registerResult2 = this.registerUser("Barbie","bonbons","barbie@gmail.com");
        assertEquals("already taken", registerResult2.getMessage());

        this.clearDatabase();
    }

}
