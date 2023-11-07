package unitTests;

import org.junit.jupiter.api.Test;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateGameTests extends BasicFunctions{
    @Test
    void createAGame() {
        var result = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        var gameResult = this.createAGame(((RegisterResult) result).getAuthToken(), "Barbenheimer");
        assertNull(gameResult.getMessage()); // null for success
    }

    @Test
    void createAGameSameName() {
        var result = this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com");
        var gameResult = this.createAGame("token not working", "Barbenheimer");
        assertEquals( "unauthorized", gameResult.getMessage());
    }
    // could check that there was an auth token that got sent
    // could check that the username is the same 

    @Test
    void clearData() {
        this.clearDatabase();
    }
}
