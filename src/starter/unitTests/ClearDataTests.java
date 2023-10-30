package unitTests;

import request.*;
import result.*;
import service.*;
import chess.ChessGameImpl;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import model.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClearDataTests extends BasicFunctions{
    @Test
    void clearApp() {
        var gameDao = new GameDAO();

        var game = new Game();
        game.setGameName("Barbenheimer");
        game.setWhiteUsername("Barbie");
        game.setBlackUsername("Oppenheimer");
        game.setGame(new ChessGameImpl());
        game.setGameID(2023);

        try {
            gameDao.insertGameIntoDatabase(game);
        }
        catch (DataAccessException e) {
            System.out.println("Server error.");
        }

        assertTrue(gameDao.isGameInDatabase(2023), "No game was written into the DB.");

        var clearService = new ClearDataService();
        var clearResult = clearService.clear();

        assertFalse(gameDao.isGameInDatabase(2023), "The clear DB function didn't work properly.");
    }
}
