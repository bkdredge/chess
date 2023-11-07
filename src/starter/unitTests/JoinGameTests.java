package unitTests;

import org.junit.jupiter.api.Test;
import result.CreateGameResult;
import result.JoinGameResult;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JoinGameTests extends BasicFunctions{
    @Test
    void joinAGame() {
        this.clearDatabase();

        // POSITIVE
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenheimer"));
        assertNull(gameResult.getMessage()); //success

        var joinGameResult = ((JoinGameResult)this.joinAGame(result.getAuthToken(), "WHITE", gameResult.getGameID()));
        assertNull(joinGameResult.getMessage()); //success
    }

    @Test
    void joinAGameNegative() {
        this.clearDatabase();

        // POSITIVE
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenheimer"));
        var gameResult2 = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenweimer"));
        var gameResult3 = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenleimer"));
        assertNull(gameResult.getMessage()); //success
        var joinGameResult = ((JoinGameResult)this.joinAGame(result.getAuthToken(), "WHITE", gameResult.getGameID()));
        assertNull(joinGameResult.getMessage() ); //success

        //NEGATIVE
        var result2 = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var joinGameResult2 = ((JoinGameResult)this.joinAGame(result2.getAuthToken(), "WHITE", gameResult.getGameID())); //same game
        assertEquals("already taken", joinGameResult2.getMessage());

        this.clearDatabase();
    }
}
