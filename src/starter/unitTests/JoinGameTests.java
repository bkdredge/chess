package unitTests;

import org.junit.jupiter.api.Test;
import result.CreateGameResult;
import result.JoinGameResult;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinGameTests extends BasicFunctions{
    @Test
    void joinAGame() {
        // POSITIVE
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenheimer"));
        assertEquals(gameResult.getMessage(), null); //success

        var joinGameResult = ((JoinGameResult)this.joinAGame(result.getAuthToken(), "WHITE", gameResult.getGameID()));
        assertEquals(joinGameResult.getMessage(), null); //success

        //NEGATIVE
        var result2 = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var joinGameResult2 = ((JoinGameResult)this.joinAGame(result2.getAuthToken(), "WHITE", gameResult.getGameID())); //samegame
        assertEquals(joinGameResult2.getMessage(), "already taken");

        this.clearDatabase();
    }
}
