package unitTests;

import org.junit.jupiter.api.Test;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListGamesTests extends BasicFunctions {
    @Test
    void listGames() {
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = this.createAGame(result.getAuthToken(), "gameName");
        assertNull(gameResult.getMessage()); //success

        var listGameRes = this.listGames(result.getAuthToken());
        assertNull(listGameRes.getMessage()); //success

    }

    @Test
    void listGamesBadAuth() {
        var result = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var listGameRes = this.listGames("token not working");
        assertEquals("unauthorized", listGameRes.getMessage());
    }

    @Test
    void clearData() {
        this.clearDatabase();
    }
}
