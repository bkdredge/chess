package unitTests;

import org.junit.jupiter.api.Test;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateGameTests extends BasicFunctions{
    @Test
    void createAGame() {
        var result = this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        var gameResult = this.createAGame(((RegisterResult) result).getAuthToken(), "Barbenheimer");
        assertEquals(gameResult.getMessage(), null);
    }

    @Test
    void createAGameSameName() {
        var result = this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com");
        var gameResult = this.createAGame("token not working", "Barbenheimer");
        assertEquals(gameResult.getMessage(), "unauthorized");
    }

    @Test
    void clearData() {
        this.clearDatabase();
    }
}
