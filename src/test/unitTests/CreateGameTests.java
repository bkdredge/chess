package unitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import result.CreateGameResult;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreateGameTests extends BasicFunctions{
    RegisterResult result1;
    RegisterResult result2;
    RegisterResult result3;
    CreateGameResult gameResult1;
    CreateGameResult gameResult2;
    CreateGameResult gameResult3;
    @Test
    @BeforeEach
    void createUsers() {
        this.clearDatabase();
        result1 = (RegisterResult) this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        result2 = (RegisterResult) this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com");
        result3 = (RegisterResult) this.registerUser("Midge", "toodles", "midge@gmail.com");
        assertNull(result1.getMessage()); // null for success
        assertNull(result2.getMessage()); // null for success
        assertNull(result3.getMessage()); // null for success
    }
    @Test
    void createAGame() {
        this.clearDatabase();
        result1 = (RegisterResult) this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        result2 = (RegisterResult) this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com");
        result3 = (RegisterResult) this.registerUser("Midge", "toodles", "midge@gmail.com");
        var gameResult = this.createAGame(result1.getAuthToken(), "Barbenheimer");
        assertNull(gameResult.getMessage()); // null for success
    }

    @Test
    void createAGameSameName() {
        this.clearDatabase();
        result1 = (RegisterResult) this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        result2 = (RegisterResult) this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com");
        result3 = (RegisterResult) this.registerUser("Midge", "toodles", "midge@gmail.com");
        var gameResult = this.createAGame("token not working", "Barbenheimer");
        assertEquals( "unauthorized", gameResult.getMessage());
    }
    // could check that there was an auth token that got sent
    // could check that the username is the same 

    @Test
    void createFirstGame() {
        this.clearDatabase();
        result1 = (RegisterResult) this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        result2 = (RegisterResult) this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com");
        result3 = (RegisterResult) this.registerUser("Midge", "toodles", "midge@gmail.com");
        gameResult1 = ((CreateGameResult)this.createAGame(result1.getAuthToken(), "Barbenheimer"));
        assertEquals( 1, gameResult1.getGameID());
    }

    @Test
    void createSecondGame() {
        createFirstGame();
        gameResult2 = ((CreateGameResult)this.createAGame(result2.getAuthToken(), "Barbenpeimer"));
        assertEquals( 2, gameResult2.getGameID());
    }
    @Test
    void createThirdGame(){
        createSecondGame();
        gameResult3 = ((CreateGameResult)this.createAGame(result3.getAuthToken(), "Midgegame"));
        assertEquals( 3, gameResult3.getGameID());
    }
}
