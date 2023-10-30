package unitTests;

import org.junit.jupiter.api.Test;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListGamesTests extends BasicFunctions {
    @Test
    void listGames() {
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = this.createAGame(result.getAuthToken(), "gameName");
        assertEquals(gameResult.getMessage(), null); //success

        var listGameRes = this.listGames(result.getAuthToken());
        assertEquals(listGameRes.getMessage(), null); //success

    }

    @Test
    void listGamesBadAuth() {
        var result = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var listGameRes = this.listGames("token not working");
        assertEquals(listGameRes.getMessage(), "unauthorized");
    }

    @Test
    void clearData() {
        this.clearDatabase();
    }
}
