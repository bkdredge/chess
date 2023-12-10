package unitTests;

import org.junit.jupiter.api.Test;
import result.CreateGameResult;
import result.ListGamesResult;
import result.RegisterResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListGamesTests extends BasicFunctions {
    RegisterResult result1;
    RegisterResult result2;
    RegisterResult result3;
    CreateGameResult gameResult1;
    CreateGameResult gameResult2;
    CreateGameResult gameResult3;
    @Test
    void listGames() {
        this.clearDatabase();
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = this.createAGame(result.getAuthToken(), "gameName");
        assertNull(gameResult.getMessage()); //success

        var listGameResult = this.listGames(result.getAuthToken());
        assertNull(listGameResult.getMessage()); //success
    }

    @Test
    void listFirstGame() {
        this.clearDatabase();
        result1 = (RegisterResult) this.registerUser("Barbie", "bonbons", "barbie@gmail.com");
        result2 = (RegisterResult) this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com");
        result3 = (RegisterResult) this.registerUser("Midge", "toodles", "midge@gmail.com");
        gameResult1 = ((CreateGameResult)this.createAGame(result1.getAuthToken(), "Barbenheimer"));
        var listGameResult = (ListGamesResult)this.listGames(result1.getAuthToken());
        assertEquals(1,listGameResult.getGames().size()); //success
    }

    @Test
    void listSecondGame() {
        listFirstGame();
        gameResult2 = ((CreateGameResult)this.createAGame(result1.getAuthToken(), "Barbenpeimer"));
        assertEquals( 2, gameResult2.getGameID());
        var listGameResult = (ListGamesResult)this.listGames(result1.getAuthToken());
        assertEquals(2,listGameResult.getGames().size());
    }
    @Test
    void listThirdGame(){
        listSecondGame();
        gameResult3 = ((CreateGameResult)this.createAGame(result1.getAuthToken(), "Midgegame"));
        assertEquals( 3, gameResult3.getGameID());
        var listGameResult = (ListGamesResult)this.listGames(result1.getAuthToken());
        assertEquals(3,listGameResult.getGames().size());
    }

    @Test
    void listGamesBadAuth() {
        this.clearDatabase();
        var result = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var listGameRes = this.listGames("token not working");
        assertEquals("unauthorized", listGameRes.getMessage());
    }
}
