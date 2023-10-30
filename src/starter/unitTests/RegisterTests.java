package unitTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTests extends BasicFunctions{
    @Test
    void registerUser() { //"Barbie","bonbons","barbie@gmail.com
        this.clearDatabase();

        var registerResult = this.registerUser("Barbie","bonbons","barbie@gmail.com");
        assertEquals(registerResult.getMessage(), null); //success

        var registerResult2 = this.registerUser("Barbie","bonbons","barbie@gmail.com");
        assertEquals(registerResult2.getMessage(), "already taken");

        this.clearDatabase();
    }

}
