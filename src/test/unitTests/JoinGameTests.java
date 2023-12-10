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
    void joinAGameNegativeWhite() {
        this.clearDatabase();

        // POSITIVE
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenheimer"));
        assertNull(gameResult.getMessage()); //success
        var joinGameResult = ((JoinGameResult)this.joinAGame(result.getAuthToken(), "WHITE", gameResult.getGameID()));
        assertNull(joinGameResult.getMessage() ); //success

        //NEGATIVE
        var result2 = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var joinGameResult2 = ((JoinGameResult)this.joinAGame(result2.getAuthToken(), "WHITE", gameResult.getGameID())); //same game
        assertEquals("already taken", joinGameResult2.getMessage());

        this.clearDatabase();
    }

    @Test
    void joinAGameNegativeBlack() {
        this.clearDatabase();

        // POSITIVE
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenheimer"));
        assertNull(gameResult.getMessage()); //success
        var joinGameResult = ((JoinGameResult)this.joinAGame(result.getAuthToken(), "BLACK", gameResult.getGameID()));
        assertNull(joinGameResult.getMessage() ); //success

        //NEGATIVE
        var result2 = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var joinGameResult2 = ((JoinGameResult)this.joinAGame(result2.getAuthToken(), "BLACK", gameResult.getGameID())); //same game
        assertEquals("already taken", joinGameResult2.getMessage());

        this.clearDatabase();
    }

    @Test
    void joinFifthGame() {
        this.clearDatabase();

        // POSITIVE
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult1 = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenheimer"));
        var gameResult2 = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenreimer"));
        var gameResult3 = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenbeimer"));
        var gameResult4 = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenleimer"));
        var gameResult5 = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenxeimer"));
        assertNull(gameResult5.getMessage()); //success
        var joinGameResult = ((JoinGameResult)this.joinAGame(result.getAuthToken(), "WHITE", gameResult5.getGameID()));
        assertNull(joinGameResult.getMessage() ); //success

        //NEGATIVE
        var result2 = ((RegisterResult)this.registerUser("Oppenheimer", "bombs", "oppenheimer@gmail.com"));
        var joinGameResult2 = ((JoinGameResult)this.joinAGame(result2.getAuthToken(), "WHITE", gameResult5.getGameID())); //same game
        assertEquals("already taken", joinGameResult2.getMessage());

        //this.clearDatabase();
    }

    @Test
    void joinAGameBadID() {
        this.clearDatabase();

        // POSITIVE
        var result = ((RegisterResult)this.registerUser("Barbie", "bonbons", "barbie@gmail.com"));
        var gameResult = ((CreateGameResult)this.createAGame(result.getAuthToken(), "Barbenheimer"));
        assertNull(gameResult.getMessage()); //success

        var joinGameResult = ((JoinGameResult)this.joinAGame(result.getAuthToken(), "WHITE", 0));
        assertEquals("bad request",joinGameResult.getMessage()); //success
    }
}
